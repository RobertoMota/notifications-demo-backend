package mx.rmotad.notifications.notifier.infrastructure.messaging;


import static mx.rmotad.notifications.notifier.infrastructure.config.NotifierRabbitConfig.NOTIFIER_DELIVERED_CREATED_QUEUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.rmotad.notifications.notifier.application.NotifierUseCases;
import mx.rmotad.notifications.notifier.application.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationRabbitListener {

  private final NotifierUseCases notifierUseCases;
  private final ObjectMapper mapper;

  @RabbitListener(queues = {NOTIFIER_DELIVERED_CREATED_QUEUE})
  public void receiveMessage(String notification) {
    log.debug("Received : [{}]", notification);
    try {
      notifierUseCases.processNotification(mapper.readValue(notification, Notification.class));
    } catch (JsonProcessingException e) {
      log.error("CouldNot read message");
    }
  }
}
