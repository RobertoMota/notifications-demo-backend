package mx.rmotad.notifications.notification.application;

import static mx.rmotad.notifications.notification.domain.model.NotificationCategory.SPORTS;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.Instant;
import lombok.SneakyThrows;
import mx.rmotad.notifications.notification.domain.HashGenerator;
import mx.rmotad.notifications.notification.domain.NotificationRepository;
import mx.rmotad.notifications.notification.domain.error.NotificationError;
import mx.rmotad.notifications.notification.domain.model.NotificationCategory;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BaseNotificationUseCasesTest {

  public static final String MESSAGE = "some category related message";

  private NotificationRepository repository;
  private NotificationProducer notificationProducer;
  private BaseNotificationUseCases service;

  private NotificationProducer producer;
  private HashGenerator hashBuilder;
  private Validator validator;

  @BeforeEach
  void setUp() {
    repository = mock(NotificationRepository.class);
    hashBuilder = mock(HashGenerator.class);
    notificationProducer = mock(NotificationProducer.class);
    service = new BaseNotificationUseCases(
        repository, hashBuilder, notificationProducer);

    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @ParameterizedTest
  @EnumSource(NotificationCategory.class)
  void testNewNotification_UseCase_whenEverythingIsOk_success(NotificationCategory category) {
    doNothing().when(repository).save(any(NotificationDomain.class));

    doNothing().when(notificationProducer)
        .notifyCreated(any(NotificationDomain.class));

    var notification = service.newNotificationUseCase(category, MESSAGE);

    assertNotNull(notification);
    assertNotNull(notification.id());
    assertTrue(Instant.now().compareTo(notification.createdAt()) > 0);

    verify(repository).save(any(NotificationDomain.class));
    verify(notificationProducer).notifyCreated(any(NotificationDomain.class));

  }


  @SneakyThrows
  @Test
  void testNewNotification_UseCase_whenAlreadyExists_error() {
    when(repository.existsByCategoryChecksum(any(NotificationCategory.class),
        anyString())).thenReturn(true);
    when(hashBuilder.calculateHash(anyString())).thenReturn("hash");

    assertThrows(NotificationError.class,
        () -> service.newNotificationUseCase(SPORTS, MESSAGE));

    verify(repository, never()).save(any());
    verify(notificationProducer, never())
        .notifyCreated(any(NotificationDomain.class));
  }
}