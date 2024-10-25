package mx.rmotad.notifications.delivery.infrastructure.messaging;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import lombok.SneakyThrows;
import mx.rmotad.notifications.delivery.application.DeliveryUseCases;
import mx.rmotad.notifications.delivery.application.model.Notification;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotificationRabbitListenerTest {

  private NotificationRabbitListener listener;
  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp(){
    objectMapper = mock(ObjectMapper.class);
    listener = new NotificationRabbitListener(mock(DeliveryUseCases.class), objectMapper);
  }

  @Test
  @SneakyThrows
  void receiveMessage_success() {
    listener.receiveMessage("");
  }

  @Test
  @SneakyThrows
  void receiveMessage_useCaseError_success() {
    when(objectMapper.readValue(anyString(), eq(Notification.class))).thenThrow(
        JsonProcessingException.class);
    listener.receiveMessage("");
  }
}