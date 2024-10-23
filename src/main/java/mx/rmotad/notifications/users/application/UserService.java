package mx.rmotad.notifications.users.application;

import java.util.List;
import mx.rmotad.notifications.users.domain.Category;
import mx.rmotad.notifications.users.domain.User;

public interface UserService {

  List<User> getUsersByCategory(Category category);
}