package mx.rmotad.notifications.users.application;

import static mx.rmotad.notifications.users.UserTestUtil.getRandomUser;
import static mx.rmotad.notifications.users.domain.Category.MOVIES;
import static mx.rmotad.notifications.users.domain.Category.SPORTS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.rmotad.notifications.users.domain.Category;
import mx.rmotad.notifications.users.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class UserApplicationServiceTest {

  private static UserApplicationService service;
  private static UsersConfig config;

  @BeforeAll
  static void setUp() {
    config = mock(UsersConfig.class);
    service = new UserApplicationService(config);
  }

  @ParameterizedTest
  @EnumSource(Category.class)
  void getUsersByCategory(Category category) {
    Map<Category, List<User>> defaultUsers = new HashMap<>();
    defaultUsers.put(category, Collections.singletonList(getRandomUser()));
    when(config.getDefaultUsers()).thenReturn(defaultUsers);
    var users = service.getUsersByCategory(category);
    assertFalse(users.isEmpty());
  }

  @Test
  void getUsersByCategory() {
    Map<Category, List<User>> defaultUsers = new HashMap<>();
    defaultUsers.put(SPORTS, Collections.singletonList(getRandomUser()));
    when(config.getDefaultUsers()).thenReturn(defaultUsers);
    var users = service.getUsersByCategory(MOVIES);
    assertTrue(users.isEmpty());
  }
}