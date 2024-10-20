package mx.rmotad.notifications.notification.notification.model;

public interface NotificationRepository {

  boolean existsByCategoryChecksum(NotificationCategory category, String s);
}
