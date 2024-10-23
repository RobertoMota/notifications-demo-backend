package mx.rmotad.notifications.notification.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.SneakyThrows;
import mx.rmotad.notifications.notification.domain.model.NotificationCategory;
import mx.rmotad.notifications.notification.domain.error.NotificationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class NotificationFactoryTest {

  public static final String MESSAGE = "";
  private final NotificationRepository repository = mock(NotificationRepository.class);
  private final HashGenerator hashGenerator = mock(HashGenerator.class);

  @SneakyThrows
  @ParameterizedTest
  @EnumSource(NotificationCategory.class)
  void testNewNotification_whenAllParametersAndNotExists_success(NotificationCategory category) {
    when(repository.existsByCategoryChecksum(category, MESSAGE)).thenReturn(false);
    when(hashGenerator.calculateHash(MESSAGE)).thenReturn(MESSAGE);
    var notification = NotificationFactory.create(category, MESSAGE, repository, hashGenerator);
    assertNotNull(notification);
    for (Field field : notification.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      assertNotNull(field.get(notification));
    }
  }

  @SneakyThrows
  @Test
  void testNewNotification_whenAllParametersButExists_throwError()
      throws IllegalAccessException {
    when(repository.existsByCategoryChecksum(NotificationCategory.SPORTS, MESSAGE))
        .thenReturn(true);
    when(hashGenerator.calculateHash(MESSAGE)).thenReturn(MESSAGE);
    assertThrows(NotificationError.class,
        () -> NotificationFactory.create(NotificationCategory.SPORTS, MESSAGE, repository,
            hashGenerator));
  }

  @SneakyThrows
  @Test
  void testNewNotification_whenAllParametersButNoHashing_throwError()
      throws IllegalAccessException {
    when(repository.existsByCategoryChecksum(NotificationCategory.SPORTS, MESSAGE))
        .thenReturn(false);
    when(hashGenerator.calculateHash(MESSAGE)).thenThrow(NoSuchAlgorithmException.class);
    assertThrows(NotificationError.class,
        () -> NotificationFactory.create(NotificationCategory.SPORTS, MESSAGE, repository,
            hashGenerator));
  }

  @Test
  void t() throws NoSuchAlgorithmException {
    var string = "hello";
    System.out.println(new String(MessageDigest.getInstance("SHA256").digest(string.getBytes())));
    System.out.println(string.hashCode());
    System.out.println(string.hashCode());
  }
}