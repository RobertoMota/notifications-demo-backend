package mx.rmotad.notifications.delivery.application;

import mx.rmotad.notifications.delivery.application.model.Notification;

/**
 * Holds the use cases for notification deliveries
 */
public interface DeliveryUseCases {

  /**
   * Delivers the given notification to its subscribers through the channels available for each one
   *
   * @param notification
   */
  void deliveryNotifications(Notification notification);
}
