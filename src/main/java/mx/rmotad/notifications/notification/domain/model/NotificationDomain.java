package mx.rmotad.notifications.notification.domain.model;


import java.time.Instant;

/**
 * This represents a Notification in the context of the business
 */
public record NotificationDomain(String id, NotificationCategory category, String content,
                                 String hash, Instant createdAt)
    implements IdentifiableResource, Notification, HashContent, CreationTimestamped {

}

