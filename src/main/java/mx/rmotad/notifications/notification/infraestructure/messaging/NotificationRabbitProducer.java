package mx.rmotad.notifications.notification.infraestructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.rmotad.notifications.notification.application.service.NotificationProducer;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationRabbitProducer implements NotificationProducer {

  public static final String ROUTING_KEY = "";
  public static final String ERROR_SENDING_MESSAGE_EX = "Error sending message ex:[{}]";
  private final RabbitTemplate rabbitTemplate;
  private final FanoutExchange fanoutExchange;
  private final ObjectMapper mapper;

  @Override
  public void notifyCreated(NotificationDomain notification) {
    try {
      String payload = mapper.writeValueAsString(notification);
      rabbitTemplate.convertAndSend(fanoutExchange.getName(), ROUTING_KEY, payload);
    } catch (Exception ex) {
      log.error(ERROR_SENDING_MESSAGE_EX, ex.getMessage());
    }
  }
}
