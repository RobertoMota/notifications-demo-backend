package mx.rmotad.notifications.users.application;

import java.util.List;
import mx.rmotad.notifications.users.domain.Category;
import mx.rmotad.notifications.users.domain.User;

public interface UserService {

  /**
   * Retrieves a list of users subscribed to a given category
   *
   * @param category the category which users are subscribed to
   * @return the list o the subscribed users
   */
  List<User> getUsersByCategory(Category category);
}
