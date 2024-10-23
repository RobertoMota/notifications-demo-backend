package mx.rmotad.notifications.notifier.application;


import mx.rmotad.notifications.notifier.application.model.Notification;

/**
 * This is intended to hold the Notification events
 */
public interface NotifierProducer {

  /**
   * @param notification
   */
  void notifyCreated(Notification notification);
}
