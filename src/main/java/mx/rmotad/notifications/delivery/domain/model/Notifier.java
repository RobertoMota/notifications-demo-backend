package mx.rmotad.notifications.delivery.domain.model;

import java.util.List;
import mx.rmotad.notifications.delivery.application.model.User;

public interface Notifier {

  List<String> notify(String message, List<User> destinationList);

}
