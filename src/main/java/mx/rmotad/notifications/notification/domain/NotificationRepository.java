package mx.rmotad.notifications.notification.domain;

import mx.rmotad.notifications.notification.domain.model.NotificationCategory;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;

/**
 * This interface acts as a notification collection or repository within the business context
 */
public interface NotificationRepository {

  /**
   * Checks if a notification with the same message and category already exists
   *
   * @param category the notification category
   * @param message  the notification message
   * @return true if already exist a notification with the provided message and category
   */
  boolean existsByCategoryChecksum(NotificationCategory category, String message);

  /**
   * Saves the notification
   *
   * @param notification the notification to be persisted
   */
  void save(NotificationDomain notification);
}
