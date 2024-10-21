package mx.rmotad.notifications.notification.domain.error;

/**
 * Error related to notifications
 */
public class NotificationError extends RuntimeException {

  public NotificationError(String message) {
    super(message);
  }
}
