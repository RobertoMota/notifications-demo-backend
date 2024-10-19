package mx.rmotad.notifications.users.application;

import java.util.List;
import mx.rmotad.notifications.users.domain.User;

public interface IUserApplicationService {

  List<User> getUsersByCategory(String category);
}
