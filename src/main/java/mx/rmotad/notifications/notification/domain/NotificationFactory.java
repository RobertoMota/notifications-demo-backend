package mx.rmotad.notifications.notification.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import java.time.Instant;
import lombok.experimental.UtilityClass;
import mx.rmotad.notifications.common.enums.NotificationCategory;
import mx.rmotad.notifications.notification.domain.error.NotificationError;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;

@UtilityClass
public class NotificationFactory {

  /**
   * Creates a new Notification Domain object
   *
   * @param category   the category of the notification
   * @param message    the message of the notification
   * @param repository the {@link NotificationRepository}
   * @return the created notification
   */
  public static NotificationDomain create(NotificationCategory category, String message,
      NotificationRepository repository, HashBuilder hashBuilder) {

    String hash = hashBuilder.calculateHash(message);

    if (repository.existsByCategoryChecksum(category, message)) {
      throw new NotificationError("Notification already exists");
    }
    return new NotificationDomain(
        UlidCreator.getMonotonicUlid().toLowerCase(), category, message, hash, Instant.now());
  }
}
