package mx.rmotad.notifications.notifier.application.service;

import java.util.List;
import java.util.Map;
import mx.rmotad.notifications.notifier.application.model.User;
import mx.rmotad.notifications.notifier.domain.model.NotifierCategory;
import mx.rmotad.notifications.notifier.domain.model.NotifierChannel;

/**
 * This interface is intended to hold the users API use cases
 */
public interface UserService {

  /**
   * Requests a list of users subscribed to the given category
   *
   * @param category the category to filter the users
   * @return a map of users if there are any subscribed user to the given category, empty list
   * otherwise
   */
  Map<NotifierChannel, List<User>> getUsersByCategory(NotifierCategory category);
}
