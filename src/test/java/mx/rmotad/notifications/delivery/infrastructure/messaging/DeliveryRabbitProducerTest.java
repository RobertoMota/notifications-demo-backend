package mx.rmotad.notifications.delivery.infrastructure.messaging;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import mx.rmotad.notifications.delivery.application.DeliveyProducer;
import mx.rmotad.notifications.delivery.application.model.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

class DeliveryRabbitProducerTest {

  private DeliveyProducer producer;
  private RabbitTemplate rabbitTemplate;
  private FanoutExchange fanoutExchange;
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp(){
    rabbitTemplate = mock(RabbitTemplate.class);
    fanoutExchange = mock(FanoutExchange.class);
    objectMapper = mock(
        ObjectMapper.class);
    producer = new DeliveryRabbitProducer(rabbitTemplate, fanoutExchange, objectMapper);
  }
  @Test
  void notifyCreated_success() {

    assertAll(()->
        producer.notifyCreated(mock(Notification.class)));
  }
  @SneakyThrows
  @Test
  void notifyCreated_whenError_success() {
//    Logger log = mock(Logger.class);
    when(objectMapper.writeValueAsString(any())).thenThrow(RuntimeException.class).thenThrow(
        JsonProcessingException.class);
//    when(LoggerFactory.getLogger(DeliveryRabbitProducer.class)).thenReturn(log);
    ArgumentCaptor<String> errorCaptor = ArgumentCaptor.forClass(String.class);
    assertAll(()->
        producer.notifyCreated(mock(Notification.class)));

//    verify(log).error(anyString());
  }

}