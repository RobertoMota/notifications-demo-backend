package mx.rmotad.notifications.notification.application.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.common.enums.NotificationCategory;
import mx.rmotad.notifications.notification.application.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final RestTemplate userRestTemplate;

  @Override
  public List<UserDTO> getUsersByCategory(NotificationCategory category) {
    var users = userRestTemplate.getForEntity("http://localhost:8080/users?category=" + category,
        UserDTO[].class).getBody();
    return users == null ? Collections.emptyList() : Arrays.asList(users);
  }


}
