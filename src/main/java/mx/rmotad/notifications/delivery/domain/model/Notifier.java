package mx.rmotad.notifications.delivery.domain.model;

import java.util.List;
import mx.rmotad.notifications.delivery.application.model.User;

/**
 * Represents the component that notifies to subscribers
 */
public interface Notifier {

  /**
   * Sends the notification content to the subscribers
   *
   * @param message         the message to be sent
   * @param destinationList the subscribers contact
   * @return List of notified users
   */
  List<String> notify(String message, List<User> destinationList);

}
