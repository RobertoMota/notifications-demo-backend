package mx.rmotad.notifications.notification.domain.model;

import java.time.Instant;

public interface CreationTimestamped {

  Instant createdAt();
}
