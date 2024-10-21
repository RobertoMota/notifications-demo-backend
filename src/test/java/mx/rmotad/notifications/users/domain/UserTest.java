package mx.rmotad.notifications.users.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import mx.rmotad.notifications.common.enums.NotificationCategory;
import mx.rmotad.notifications.common.enums.NotificationChannel;
import org.junit.jupiter.api.Test;

@Slf4j
class UserTest {

  @Test
  void createUser_whenAllParameters_success() throws IllegalAccessException {
    var user = new User("userId", "user", "", "", new NotificationCategory[]{},
        new NotificationChannel[]{});
    assertNotNull(user);
    for (Field field : user.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      assertNotNull(field.get(user));
    }
  }

  @Test
  void createUser_allNull_success() {
    var u = IntStream.range(0, 3)
        .mapToObj(i -> new User(UUID.randomUUID().toString(), "", "", "", null, null))
        .collect(Collectors.toList());
    log.info("{}", u);

  }
}