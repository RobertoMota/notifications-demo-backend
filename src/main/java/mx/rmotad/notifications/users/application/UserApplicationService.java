package mx.rmotad.notifications.users.application;

import jakarta.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.users.domain.Category;
import mx.rmotad.notifications.users.domain.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
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
    if(!config.defaultUsers.containsKey(category))
      return Collections.emptyList();
    return config.defaultUsers.get(category);
  }


}
