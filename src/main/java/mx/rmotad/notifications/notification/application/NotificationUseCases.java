package mx.rmotad.notifications.notification.application;

import mx.rmotad.notifications.notification.domain.model.NotificationCategory;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;

/**
 * This service holds the use cases for notifications
 */
public interface NotificationUseCases {

  /**
   * This use case handles all the process of creating a new notification
   *
   * @param category the category of the notification, e.g. {@link NotificationCategory#SPORTS} see
   *                 {@link NotificationCategory}
   * @param message  the text message of the notification
   * @return the created notification
   */
  NotificationDomain newNotificationUseCase(NotificationCategory category, String message);
}
