package mx.rmotad.notifications.notification.domain;

import static mx.rmotad.notifications.notification.domain.error.ErrorConstants.NOTIFICATION_ALREADY_EXISTS;

import com.github.f4b6a3.ulid.UlidCreator;
import io.vavr.control.Try;
import java.security.NoSuchAlgorithmException;
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
      NotificationRepository repository, HashGenerator hashGenerator) {

    return Try.of(() -> hashGenerator.calculateHash(message))
        .andThen(hash -> validateIfExists(category, hash, repository))
        .map(hash -> createNotificationDomain(category, message, hash))
        .onFailure(NoSuchAlgorithmException.class, (ex) -> {
          throw new NotificationError("Could not hash content");
        }).get();
  }

  private static NotificationDomain createNotificationDomain(NotificationCategory category,
      String content, String hash) {
    return new NotificationDomain(
        UlidCreator.getMonotonicUlid().toLowerCase(), category, content, hash, Instant.now());
  }

  private static void validateIfExists(NotificationCategory category, String message,
      NotificationRepository repository) {
    if (repository.existsByCategoryChecksum(category, message)) {
      throw new NotificationError(NOTIFICATION_ALREADY_EXISTS);
    }
  }
}
