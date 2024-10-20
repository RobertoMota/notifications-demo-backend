package mx.rmotad.notifications.notification.notification.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import mx.rmotad.notifications.notification.notification.error.NotificationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class NotificationTest {

  private final NotificationRepository repository = mock(NotificationRepository.class);

  @ParameterizedTest
  @EnumSource(NotificationCategory.class)
  void testNewNotification_whenAllParametersAndNotExists_success(NotificationCategory category)
      throws IllegalAccessException {
    when(repository.existsByCategoryChecksum(category, "")).thenReturn(false);
    var notification = Notification.create(category, "", repository);
    assertNotNull(notification);
    for (Field field : notification.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      assertNotNull(field.get(notification));
    }
  }

  @Test
  void testNewNotification_whenAllParametersButExists_throwError()
      throws IllegalAccessException {
    when(repository.existsByCategoryChecksum(NotificationCategory.SPORTS, ""))
        .thenReturn(true);

    assertThrows(NotificationError.class,
        () -> Notification.create(NotificationCategory.SPORTS, "", repository));
  }
}