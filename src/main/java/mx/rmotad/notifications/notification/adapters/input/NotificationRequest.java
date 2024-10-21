package mx.rmotad.notifications.notification.adapters.input;

import mx.rmotad.notifications.common.enums.NotificationCategory;
import mx.rmotad.notifications.notification.domain.model.Notification;

public record NotificationRequest(NotificationCategory category, String message)
    implements Notification {

}
