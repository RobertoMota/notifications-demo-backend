package mx.rmotad.notifications.notification.application;

import static mx.rmotad.notifications.common.enums.NotificationCategory.SPORTS;
import static mx.rmotad.notifications.notification.NotificationTestUtils.getRandomUserList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.f4b6a3.ulid.UlidCreator;
import java.time.Instant;
import java.util.Collections;
import mx.rmotad.notifications.common.enums.NotificationCategory;
import mx.rmotad.notifications.common.enums.NotificationChannel;
import mx.rmotad.notifications.notification.application.service.INotificationProducerFactory;
import mx.rmotad.notifications.notification.application.service.IUserService;
import mx.rmotad.notifications.notification.application.service.NotificationProducer;
import mx.rmotad.notifications.notification.domain.HashBuilder;
import mx.rmotad.notifications.notification.domain.NotificationRepository;
import mx.rmotad.notifications.notification.domain.error.NotificationError;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class BaseNotificationDomainUseCasesTest {

  public static final String MESSAGE = "some category related message";
  private NotificationRepository repository;
  private IUserService userService;
  private INotificationProducerFactory INotificationProducerFactory;
  private BaseNotificationUseCases service;

  private NotificationProducer producer;
  private HashBuilder hasBuilder;

  @BeforeEach
  void setUp() {
    producer = mock(NotificationProducer.class);
    repository = mock(NotificationRepository.class);
    hasBuilder = mock(HashBuilder.class);
    userService = mock(IUserService.class);
    INotificationProducerFactory = mock(INotificationProducerFactory.class);
    service = new BaseNotificationUseCases(
        repository, hasBuilder, userService, INotificationProducerFactory);
  }

  @ParameterizedTest
  @EnumSource(NotificationCategory.class)
  void testNewNotification_UseCase_whenEverythingIsOk_success(NotificationCategory category) {
    doNothing().when(repository).save(any(NotificationDomain.class));

    when(userService.getUsersByCategory(any(NotificationCategory.class))).thenReturn(
        getRandomUserList());
    doReturn(producer).when(INotificationProducerFactory)
        .getProducerForChannel(any(NotificationChannel.class));

    var notification = service.newNotificationUseCase(category, MESSAGE);
    assertNotNull(notification);
    assertNotNull(notification.id());
    assertTrue(UlidCreator.getUlid().toLowerCase().compareTo(notification.id()) > 0);
    assertTrue(Instant.now().compareTo(notification.createdAt()) > 0);

    verify(repository).save(any(NotificationDomain.class));
    verify(userService).getUsersByCategory(any(NotificationCategory.class));

    verify(INotificationProducerFactory, times(NotificationChannel.values().length)).
        getProducerForChannel(any(NotificationChannel.class));
    verify(producer, times(NotificationChannel.values().length)).notifyUsers(
        any(NotificationDomain.class),
        anyList());
  }

  @Test
  void testNewNotification_UseCase_whenNotSubscribedUsers_success() {
    doNothing().when(repository).save(any(NotificationDomain.class));
    when(userService.getUsersByCategory(any(NotificationCategory.class))).thenReturn(
        Collections.emptyList());

    var notification = service.newNotificationUseCase(SPORTS, MESSAGE);
    assertNotNull(notification);

    verify(repository).save(any(NotificationDomain.class));
    verify(userService).getUsersByCategory(any(NotificationCategory.class));
    verify(INotificationProducerFactory, never()).getProducerForChannel(
        any(NotificationChannel.class));
  }

  @Test
  void testNewNotification_UseCase_whenAlreadyExists_error() {
    when(repository.existsByCategoryChecksum(any(NotificationCategory.class),
        anyString())).thenReturn(true);

    assertThrows(NotificationError.class,
        () -> service.newNotificationUseCase(SPORTS, MESSAGE));

    verify(repository, never()).save(any());
    verify(userService, never()).getUsersByCategory(any());
    verify(INotificationProducerFactory, never()).getProducerForChannel(
        any(NotificationChannel.class));

  }
}