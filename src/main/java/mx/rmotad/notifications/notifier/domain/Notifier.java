package mx.rmotad.notifications.notifier.domain;

import java.util.List;

public interface Notifier {

  void notify(String message, List<?> destinationList);

}
