package mx.rmotad.notifications.notification.application.service;

import java.util.List;
import mx.rmotad.notifications.notification.application.dto.UserDTO;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;

/**
 * This is intended to hold the Notification events
 */
public interface NotificationProducer {

  /**
   * @param notification
   * @param users
   */
  void notifyUsers(NotificationDomain notification, List<UserDTO> users);
}
