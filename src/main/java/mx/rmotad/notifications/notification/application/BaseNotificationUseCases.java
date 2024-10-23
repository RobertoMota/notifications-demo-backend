package mx.rmotad.notifications.notification.application;

import io.vavr.control.Try;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.common.UseCase;
import mx.rmotad.notifications.notification.domain.HashGenerator;
import mx.rmotad.notifications.notification.domain.NotificationFactory;
import mx.rmotad.notifications.notification.domain.NotificationRepository;
import mx.rmotad.notifications.notification.domain.model.NotificationCategory;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;

@UseCase
@RequiredArgsConstructor
public class BaseNotificationUseCases implements NotificationUseCases {

  private final NotificationRepository notificationRepository;
  private final HashGenerator hashGenerator;
  private final NotificationProducer notificationProducer;

  @Override
  public NotificationDomain newNotificationUseCase(@NotNull NotificationCategory category,
      @NotNull @Size(min = 1, max = 130) String message) {

    return Try.of(
            () -> NotificationFactory.create(category, message, notificationRepository, hashGenerator))
        .andThen(notificationRepository::save)
        .andThen(notificationProducer::notifyCreated)
        .get();

  }

}
