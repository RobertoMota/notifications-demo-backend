package mx.rmotad.notifications.notification.notification.model;


import com.github.f4b6a3.ulid.UlidCreator;
import java.time.Instant;
import lombok.Getter;
import mx.rmotad.notifications.notification.notification.error.NotificationError;

@Getter
public class Notification {

  private final String id;
  private final NotificationCategory category;
  private final String message;
  private final Instant createdAt;

  private Notification(NotificationCategory category, String message) {

    this.id = UlidCreator.getMonotonicUlid().toLowerCase();
    this.category = category;
    this.message = message;
    this.createdAt = Instant.now();
  }

  public static Notification create(NotificationCategory category, String message,
      NotificationRepository repository) {
    if (repository.existsByCategoryChecksum(category, message)) {
      throw new NotificationError("Notification already exists");
    }
    return new Notification(category, message);
  }

}
