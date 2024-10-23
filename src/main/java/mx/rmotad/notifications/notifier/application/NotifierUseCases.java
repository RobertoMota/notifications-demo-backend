package mx.rmotad.notifications.notifier.application;

import io.vavr.control.Try;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.rmotad.notifications.common.UseCase;
import mx.rmotad.notifications.notifier.application.model.Notification;
import mx.rmotad.notifications.notifier.application.model.User;
import mx.rmotad.notifications.notifier.application.service.UserApplicationService;
import mx.rmotad.notifications.notifier.domain.NotifierFactory;
import mx.rmotad.notifications.notifier.domain.model.NotifierChannel;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class NotifierUseCases {

  private final UserApplicationService userApplicationService;
  private final NotifierFactory notifierFactory;

  public void processNotification(Notification notification) {

    Map<NotifierChannel, List<User>> subscriberUsers = new HashMap<>();
    subscriberUsers = userApplicationService.getUsersByCategory(notification.category());
    subscriberUsers.forEach(
        (channel, users) -> {
          Try.of(() -> notifierFactory.getNotifier(channel))
              .andThen(notifier -> notifier.notify(notification.content(), users))
              .onFailure(Exception.class,
                  (ex) -> log.warn("Could not send notification [{}] through [{}] : [{}] ",
                      notification.id(), channel, ex.getMessage()));
        }
    );

  }
}
