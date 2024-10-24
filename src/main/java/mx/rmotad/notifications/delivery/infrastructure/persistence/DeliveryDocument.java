package mx.rmotad.notifications.delivery.infrastructure.persistence;

import java.time.Instant;
import java.util.List;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationChannel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deliveries")
public record DeliveryDocument(@Id String id, String notificationId,
                               DeliveryNotificationChannel channel,
                               List<String> destinations, Instant createdAt) {

}
