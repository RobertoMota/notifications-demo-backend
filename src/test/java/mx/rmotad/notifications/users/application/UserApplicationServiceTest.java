package mx.rmotad.notifications.users.application;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserApplicationServiceTest {

  private static UserApplicationService service;

  @BeforeAll
  static void setUp() {
    service = new UserApplicationService();
    service.setUp();
  }

  @ParameterizedTest
  @ValueSource(strings = {"SPORTS", "MOVIES", "FINANCE"})
  void getUsersByCategory(String category) {
    var users = service.getUsersByCategory(category);
    assertFalse(users.isEmpty());
  }
}