package mx.rmotad.notifications.notification.application.service;

import mx.rmotad.notifications.common.enums.NotificationChannel;

/**
 * Producer factory
 */
public interface INotificationProducerFactory {

  /**
   * Returns the appropriated producer for the given channel
   *
   * @param channel the channel to match the producer
   * @return the producer for the given channel
   */
  NotificationProducer getProducerForChannel(NotificationChannel channel);
}
