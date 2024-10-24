package mx.rmotad.notifications.notification.application.service;

import static mx.rmotad.notifications.notification.NotificationTestUtils.getRandomUserArray;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import mx.rmotad.notifications.delivery.application.model.User;
import mx.rmotad.notifications.delivery.application.service.UserApplicationService;
import mx.rmotad.notifications.delivery.application.service.UserService;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class UserApplicationServiceTest {

  public static final DeliveryNotificationCategory NOTIFICATION_CATEGORY = DeliveryNotificationCategory.SPORTS;
  private UserService service;
  private RestTemplate restTemplate;

  @BeforeEach
  void setUp() {
    this.restTemplate = mock(RestTemplate.class);
    this.service = new UserApplicationService(restTemplate);
  }

  @Test
  void testGetUsersByCategory_whenUsersExists_success() {
    when(restTemplate.getForEntity(anyString(), eq(User[].class)))
        .thenReturn(ResponseEntity.ok().body(getRandomUserArray()));

    var users = service.getUsersByCategory(NOTIFICATION_CATEGORY);
    assertNotNull(users);
    assertFalse(users.isEmpty());
    verify(restTemplate).getForEntity(anyString(), eq(User[].class));
  }

  @Test
  void testGetUsersByCategory_whenNoUsers_returnEmptyList() {

    when(restTemplate.getForEntity(anyString(), eq(User[].class)))
        .thenReturn(ResponseEntity.ok(new User[]{}));

    var users = service.getUsersByCategory(NOTIFICATION_CATEGORY);
    assertNotNull(users);
    verify(restTemplate).getForEntity(anyString(), eq(User[].class));
  }

  @Test
  void testGetUsersByCategory_whenBodyNull_returnEmptyList() {

    when(restTemplate.getForEntity(anyString(), eq(User[].class)))
        .thenReturn(ResponseEntity.ok(null));

    var users = service.getUsersByCategory(NOTIFICATION_CATEGORY);
    assertNotNull(users);
    verify(restTemplate).getForEntity(anyString(), eq(User[].class));
  }
}