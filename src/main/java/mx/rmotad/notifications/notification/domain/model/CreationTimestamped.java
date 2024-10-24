package mx.rmotad.notifications.notification.domain.model;

import java.time.Instant;

/**
 * Represents a resource which has a timestamp of the instant it was created
 */
public interface CreationTimestamped {

  Instant createdAt();
}
