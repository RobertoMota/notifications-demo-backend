package mx.rmotad.notifications.notification.notification.application.service;

import static mx.rmotad.notifications.notification.notification.model.NotificationCategory.SPORTS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import mx.rmotad.notifications.notification.notification.application.dto.UserDTO;
import mx.rmotad.notifications.notification.notification.model.NotificationCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class UserServiceTest {

  public static final NotificationCategory NOTIFICATION_CATEGORY = SPORTS;
  private IUserService service;
  private RestTemplate restTemplate;

  @BeforeEach
  void setUp() {
    this.restTemplate = mock(RestTemplate.class);
    this.service = new UserService(restTemplate);
  }

  @Test
  void testGetUsersByCategory_whenUsersExists_success() {

    when(restTemplate.getForEntity(anyString(), eq(UserDTO[].class)))
        .thenReturn(ResponseEntity.ok(new UserDTO[]{mock(UserDTO.class)}));

    var users = service.getUsersByCategory(NOTIFICATION_CATEGORY);
    assertNotNull(users);
    assertFalse(users.isEmpty());
    verify(restTemplate).getForEntity(anyString(), eq(UserDTO[].class));
  }

  @Test
  void testGetUsersByCategory_whenNoUsers_returnEmptyList() {

    when(restTemplate.getForEntity(anyString(), eq(UserDTO[].class)))
        .thenReturn(ResponseEntity.ok(new UserDTO[]{}));

    var users = service.getUsersByCategory(NOTIFICATION_CATEGORY);
    assertNotNull(users);
    verify(restTemplate).getForEntity(anyString(), eq(UserDTO[].class));
  }

  @Test
  void testGetUsersByCategory_whenBodyNull_returnEmptyList() {

    when(restTemplate.getForEntity(anyString(), eq(UserDTO[].class)))
        .thenReturn(ResponseEntity.ok(null));

    var users = service.getUsersByCategory(NOTIFICATION_CATEGORY);
    assertNotNull(users);
    verify(restTemplate).getForEntity(anyString(), eq(UserDTO[].class));
  }
}