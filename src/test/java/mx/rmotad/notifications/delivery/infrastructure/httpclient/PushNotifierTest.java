package mx.rmotad.notifications.delivery.infrastructure.httpclient;

import static org.mockito.Mockito.mock;

import java.util.Collections;
import mx.rmotad.notifications.delivery.application.model.User;
import mx.rmotad.notifications.delivery.domain.model.Notifier;
import org.junit.jupiter.api.Test;

class PushNotifierTest {


  public static final String MESSAGE = "Message";

  @Test
  void testNotify_success() {
    Notifier notifier = new PushNotifier();
    notifier.notify(MESSAGE, Collections.singletonList(mock(User.class)));
  }
}