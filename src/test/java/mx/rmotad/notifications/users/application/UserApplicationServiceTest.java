package mx.rmotad.notifications.users.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

import mx.rmotad.notifications.users.domain.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class UserApplicationServiceTest {

  private static UserApplicationService service;

  @BeforeAll
  static void setUp() {
    service = new UserApplicationService(mock(UsersConfig.class));
  }

  @ParameterizedTest
  @EnumSource(Category.class)
  void getUsersByCategory(Category category) {
    var users = service.getUsersByCategory(category);
    assertFalse(users.isEmpty());
  }
}