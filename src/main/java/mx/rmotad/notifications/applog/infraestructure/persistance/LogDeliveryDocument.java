package mx.rmotad.notifications.applog.infraestructure.persistance;

import java.time.Instant;
import java.util.List;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationChannel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deliveries")
public record LogDeliveryDocument(@Id String id, String notificationId,
                                  DeliveryNotificationChannel channel,
                                  List<String> destinations, Instant createdAt) {

}
