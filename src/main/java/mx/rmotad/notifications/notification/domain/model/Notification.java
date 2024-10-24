package mx.rmotad.notifications.notification.domain.model;

/**
 * Represents a notification related to some category
 */
public interface Notification {

  NotificationCategory category();

  String content();

}
