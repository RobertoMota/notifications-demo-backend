package mx.rmotad.notifications.notification.infraestructure.messaging;

import static mx.rmotad.notifications.notification.NotificationTestUtils.createRandomNotificationDomain;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mx.rmotad.notifications.notification.application.NotificationProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

class NotifierRabbitProducerTest {


  private NotificationProducer producer;
  private RabbitTemplate rabbitTemplate;
  private FanoutExchange fanoutExchange;
  private ObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = mock(ObjectMapper.class);
    rabbitTemplate = mock(RabbitTemplate.class);
    fanoutExchange = mock(FanoutExchange.class);
    producer = new NotificationRabbitProducer(
        rabbitTemplate, fanoutExchange, mapper
    );
  }

  @Test
  void notifyNotified() {
    producer.notifyCreated(createRandomNotificationDomain());
  }

  @Test
  void notifyNotified_error() throws JsonProcessingException {
    when(mapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);
    producer.notifyCreated(createRandomNotificationDomain());
  }

}