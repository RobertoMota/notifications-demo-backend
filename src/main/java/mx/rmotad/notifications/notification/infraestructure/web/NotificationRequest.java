package mx.rmotad.notifications.notification.infraestructure.web;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import mx.rmotad.notifications.notification.domain.model.Notification;
import mx.rmotad.notifications.notification.domain.model.NotificationCategory;

public record NotificationRequest(@NotNull NotificationCategory category,
                                  @NotNull @Size(min = 1, max = 130) String content)
    implements Notification {

}
