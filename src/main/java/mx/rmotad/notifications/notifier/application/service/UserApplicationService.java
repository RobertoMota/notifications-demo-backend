package mx.rmotad.notifications.notifier.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.notifier.application.model.User;
import mx.rmotad.notifications.notifier.domain.model.NotifierCategory;
import mx.rmotad.notifications.notifier.domain.model.NotifierChannel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserApplicationService implements UserService {

  private final RestTemplate userRestTemplate;

  @Override
  public Map<NotifierChannel, List<User>> getUsersByCategory(NotifierCategory category) {
    var users = userRestTemplate.getForEntity("http://localhost:8080/users?category=" + category,
        User[].class).getBody();
    return groupUsersByCategory(users);
  }

  private Map<NotifierChannel, List<User>> groupUsersByCategory(User[] userDTO) {
    Map<NotifierChannel, List<User>> groupedUsers = new HashMap<>();
    if (userDTO == null) {
      return groupedUsers;
    }

    for (User user : userDTO) {
      user.channels().forEach(channel -> {
        groupedUsers.putIfAbsent(channel, new ArrayList<>());
        groupedUsers.get(channel).add(user);
      });
    }
    return groupedUsers;
  }


}
