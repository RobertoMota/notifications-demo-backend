package mx.rmotad.notifications.notification.application.service;

import java.util.List;
import mx.rmotad.notifications.common.enums.NotificationCategory;
import mx.rmotad.notifications.notification.application.dto.UserDTO;

/**
 * This interface is intended to hold the users API use cases
 */
public interface IUserService {

  /**
   * Requests a list of users subscribed to the given category
   *
   * @param category the category to filter the users
   * @return a list of users if there are any subscribed user to the given category, empty list
   * otherwise
   */
  List<UserDTO> getUsersByCategory(NotificationCategory category);
}
