package mx.rmotad.notifications.delivery.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.delivery.application.model.User;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationCategory;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationChannel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserApplicationService implements UserService {

  private final RestTemplate userRestTemplate;

  @Override
  public Map<DeliveryNotificationChannel, List<User>> getUsersByCategory(
      DeliveryNotificationCategory category) {
    var users = userRestTemplate.getForEntity("http://localhost:8080/users?category=" + category,
        User[].class).getBody(); //TODO
    return groupUsersByCategory(users);
  }

  private Map<DeliveryNotificationChannel, List<User>> groupUsersByCategory(User[] userDTO) {
    Map<DeliveryNotificationChannel, List<User>> groupedUsers = new HashMap<>();
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
