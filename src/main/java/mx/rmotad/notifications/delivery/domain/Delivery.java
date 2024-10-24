package mx.rmotad.notifications.delivery.domain;

import java.time.Instant;
import java.util.List;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationChannel;

public record Delivery(String id, String notificationId,
                       DeliveryNotificationChannel channel,
                       List<String> destinations, boolean result, Instant createdAt) {

}
