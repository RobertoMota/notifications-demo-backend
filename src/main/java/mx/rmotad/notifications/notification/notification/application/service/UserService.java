package mx.rmotad.notifications.notification.notification.application.service;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.notification.notification.application.dto.UserDTO;
import mx.rmotad.notifications.notification.notification.model.NotificationCategory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final RestTemplate restTemplate;

  @Override
  public List<UserDTO> getUsersByCategory(NotificationCategory category) {
    var users = restTemplate.getForEntity("", UserDTO[].class).getBody();
    users = users == null ? new UserDTO[]{} : users;
    return Arrays.asList(users);
  }
}
