package mx.rmotad.notifications.delivery.infrastructure.messaging;


import static mx.rmotad.notifications.delivery.infrastructure.config.NotifierRabbitConfig.NOTIFIER_DELIVERED_CREATED_QUEUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.rmotad.notifications.delivery.application.DeliveryUseCases;
import mx.rmotad.notifications.delivery.application.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationRabbitListener {

  private final DeliveryUseCases deliveryUseCases;
  private final ObjectMapper mapper;

  /**
   * Receives the notification created event
   *
   * @param notification the created notification
   */
  @RabbitListener(queues = {NOTIFIER_DELIVERED_CREATED_QUEUE})
  public void receiveMessage(String notification) {
    log.debug("Received : [{}]", notification);
    try {
      deliveryUseCases.deliveryNotifications(mapper.readValue(notification, Notification.class));
    } catch (JsonProcessingException e) {
      log.error("CouldNot read message");
    }
  }
}
