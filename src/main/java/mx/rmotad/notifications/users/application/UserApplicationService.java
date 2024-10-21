package mx.rmotad.notifications.users.application;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.users.domain.Category;
import mx.rmotad.notifications.users.domain.User;
import org.springframework.stereotype.Service;

/**
 * This class is intended to hold the application use cases for User domain.
 */
@Service
@RequiredArgsConstructor
public class UserApplicationService implements IUserApplicationService {

  private final UsersConfig config;

  @Override
  public List<User> getUsersByCategory(Category category) {
    if (!config.getDefaultUsers().containsKey(category)) {
      return Collections.emptyList();
    }
    return config.getDefaultUsers().get(category);
  }


}
