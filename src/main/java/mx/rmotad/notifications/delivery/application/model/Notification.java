package mx.rmotad.notifications.delivery.application.model;

import java.time.Instant;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationCategory;

public record Notification(String id, DeliveryNotificationCategory category, String content,
                           String hash, Instant createdAt) {

}