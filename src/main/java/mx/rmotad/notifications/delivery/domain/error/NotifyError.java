package mx.rmotad.notifications.delivery.domain.error;

public class NotifyError extends RuntimeException {

  public NotifyError(String message) {
    super(message);
  }
}
