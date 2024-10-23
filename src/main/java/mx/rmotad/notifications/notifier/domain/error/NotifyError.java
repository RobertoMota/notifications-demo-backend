package mx.rmotad.notifications.notifier.domain.error;

public class NotifyError extends RuntimeException {

  public NotifyError(String message) {
    super(message);
  }
}
