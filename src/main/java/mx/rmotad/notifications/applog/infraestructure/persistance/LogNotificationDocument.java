package mx.rmotad.notifications.applog.infraestructure.persistance;

import java.time.Instant;
import mx.rmotad.notifications.notification.domain.model.CreationTimestamped;
import mx.rmotad.notifications.notification.domain.model.HashContent;
import mx.rmotad.notifications.notification.domain.model.IdentifiableResource;
import mx.rmotad.notifications.notification.domain.model.Notification;
import mx.rmotad.notifications.notification.domain.model.NotificationCategory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
public record LogNotificationDocument(@Id String id, NotificationCategory category,
                                      String content, String hash, Instant createdAt)
    implements Notification, IdentifiableResource, HashContent, CreationTimestamped {

}
