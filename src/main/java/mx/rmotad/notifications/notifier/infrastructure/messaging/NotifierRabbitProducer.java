package mx.rmotad.notifications.notifier.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.rmotad.notifications.notifier.application.NotifierProducer;
import mx.rmotad.notifications.notifier.application.model.Notification;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotifierRabbitProducer implements NotifierProducer {

  public static final String ROUTING_KEY = "";
  public static final String ERROR_SENDING_MESSAGE_EX = "Error sending message ex:[{}]";
  private final RabbitTemplate notifierRabbitTemplate;
  private final FanoutExchange notifierFanoutExchange;
  private final ObjectMapper mapper;

  @Override
  public void notifyCreated(Notification notification) {
    try {
      String payload = mapper.writeValueAsString(notification);
      notifierRabbitTemplate.convertAndSend(notifierFanoutExchange.getName(), ROUTING_KEY, payload);
    } catch (Exception ex) {
      log.error(ERROR_SENDING_MESSAGE_EX, ex.getMessage());
    }
  }
}
