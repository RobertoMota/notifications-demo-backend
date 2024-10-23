package mx.rmotad.notifications.notifier.infrastructure.messaging;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.rmotad.notifications.notifier.domain.Notifier;
import mx.rmotad.notifications.notifier.domain.error.NotifyError;

@Slf4j
public class EmailNotifier implements Notifier {

  @Override
  public void notify(String message, List<?> destinationList) {

    throw new NotifyError("not implemented yet");
  }
}
