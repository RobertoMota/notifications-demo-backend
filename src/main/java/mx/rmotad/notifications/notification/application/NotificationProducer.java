package mx.rmotad.notifications.notification.application;

import mx.rmotad.notifications.notification.domain.model.NotificationDomain;

/**
 * This is intended to hold the Notification events
 */
public interface NotificationProducer {

  /**
   * @param notification
   */
  void notifyCreated(NotificationDomain notification);
}
