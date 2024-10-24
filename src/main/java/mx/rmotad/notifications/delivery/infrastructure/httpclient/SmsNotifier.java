package mx.rmotad.notifications.delivery.infrastructure.httpclient;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.rmotad.notifications.delivery.application.model.User;
import mx.rmotad.notifications.delivery.domain.error.NotifyError;
import mx.rmotad.notifications.delivery.domain.model.Notifier;

@Slf4j
public class SmsNotifier implements Notifier {

  @Override
  public List<String> notify(String message, List<User> destinationList) {
    throw new NotifyError("not implemented yet");
  }
}
