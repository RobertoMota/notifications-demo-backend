package mx.rmotad.notifications.notification.application;

import mx.rmotad.notifications.notification.domain.model.NotificationDomain;

/**
 * This is intended to hold the Notification events
 */
public interface NotificationProducer {

  /**
   * Notifies when a notification has been created
   *
   * @param notification the created notification
   */
  void notifyCreated(NotificationDomain notification);
}
