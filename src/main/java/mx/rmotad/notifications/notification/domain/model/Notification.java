package mx.rmotad.notifications.notification.domain.model;

public interface Notification {

  NotificationCategory category();

  String content();

}
