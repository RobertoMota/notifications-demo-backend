package mx.rmotad.notifications.notification.domain.error;

/**
 * Error related to notifications
 */
public class NotificationError extends RuntimeException {

  private String errorCode;
  public NotificationError(String errorCode) {
    super(errorCode);
  }
}
