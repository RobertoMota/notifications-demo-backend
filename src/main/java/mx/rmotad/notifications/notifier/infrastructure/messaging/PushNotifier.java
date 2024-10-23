package mx.rmotad.notifications.notifier.infrastructure.messaging;

import java.util.List;
import mx.rmotad.notifications.notifier.domain.Notifier;
import mx.rmotad.notifications.notifier.domain.error.NotifyError;

public class PushNotifier implements Notifier {

  @Override
  public void notify(String message, List<?> destinationList) {
    throw new NotifyError("not implemented yet");
  }
}
