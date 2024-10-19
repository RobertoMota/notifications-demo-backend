package mx.rmotad.notifications.users.application;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.rmotad.notifications.users.domain.User;
import org.springframework.stereotype.Service;

/**
 * This class is intended to hold the application use cases for User domain.
 */
@Service
public class UserApplicationService implements IUserApplicationService {

  private Map<String, List<User>> usersByCategory;

  @PostConstruct
  void setUp() {
    //TODO generate
    usersByCategory = new HashMap<>();
    usersByCategory.put("SPORTS", List.of(new User("sId", "", "", "", null, null)));
    usersByCategory.put("FINANCE", List.of(new User("fId", "", "", "", null, null)));
    usersByCategory.put("MOVIES", List.of(new User("mId", "", "", "", null, null)));
  }

  @Override
  public List<User> getUsersByCategory(String category) {
    return usersByCategory.get(category);
  }
}
