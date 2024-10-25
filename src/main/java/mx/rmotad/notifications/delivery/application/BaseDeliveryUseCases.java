package mx.rmotad.notifications.delivery.application;

import io.vavr.control.Try;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.rmotad.notifications.common.UseCase;
import mx.rmotad.notifications.delivery.application.model.Notification;
import mx.rmotad.notifications.delivery.application.model.User;
import mx.rmotad.notifications.delivery.application.service.UserApplicationService;
import mx.rmotad.notifications.delivery.application.service.UserService;
import mx.rmotad.notifications.delivery.domain.DeliveryFactory;
import mx.rmotad.notifications.delivery.domain.DeliveryRepository;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationChannel;
import mx.rmotad.notifications.delivery.domain.model.NotifierFactory;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class BaseDeliveryUseCases implements DeliveryUseCases {

  public static final String COULD_NOT_SEND_NOTIFICATION = "Could not send notification [{}] through [{}] : [{}] ";
  private final UserService userApplicationService;
  private final NotifierFactory notifierFactory;
  private final DeliveryRepository deliveryRepository;

  @Override
  public void deliveryNotifications(Notification notification) {

    Map<DeliveryNotificationChannel, List<User>> subscriberUsers = new HashMap<>();
    subscriberUsers = userApplicationService.getUsersByCategory(notification.category());
    log.info("{}", subscriberUsers);
    subscriberUsers.forEach(
        (channel, users) -> {
          Try.of(() -> notifierFactory.getNotifier(channel))
              .map(notifier -> notifier.notify(notification.content(), users))
              .map(destinations -> DeliveryFactory.create(notification.id(), channel,
                  destinations, !destinations.isEmpty()))
              .andThen(deliveryRepository::save)
              .onFailure(Exception.class,
                  (ex) -> log.warn(COULD_NOT_SEND_NOTIFICATION,
                      notification.id(), channel, ex.getMessage()));
        }
    );

  }
}
