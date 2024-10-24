package mx.rmotad.notifications.delivery.application;


import mx.rmotad.notifications.delivery.application.model.Notification;

/**
 * This is intended to hold the Notification events
 */
public interface DeliveyProducer {

  /**
   * @param notification
   */
  void notifyCreated(Notification notification);
}
