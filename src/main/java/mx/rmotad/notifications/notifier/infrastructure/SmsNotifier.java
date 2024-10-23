package mx.rmotad.notifications.notifier.infrastructure;

import java.util.List;
import mx.rmotad.notifications.notifier.domain.Notifier;
import mx.rmotad.notifications.notifier.domain.error.NotifyError;

public class SmsNotifier implements Notifier {

  @Override
  public void notify(String message, List<?> destinationList) {

    throw new NotifyError("not implemented yet");
  }
}
