package mx.rmotad.notifications.notification.notification.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import mx.rmotad.notifications.notification.notification.application.dto.UserDTO;
import mx.rmotad.notifications.notification.notification.application.service.IMessagingService;
import mx.rmotad.notifications.notification.notification.application.service.IUserService;
import mx.rmotad.notifications.notification.notification.error.NotificationError;
import mx.rmotad.notifications.notification.notification.model.Notification;
import mx.rmotad.notifications.notification.notification.model.NotificationCategory;
import mx.rmotad.notifications.notification.notification.model.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotificationApplicationServiceTest {

  private NotificationRepository repository;
  private IUserService userService;
  private IMessagingService messagingService;
  private NotificationApplicationApplicationService service;

  @BeforeEach
  void setUp() {
    repository = mock(NotificationRepository.class);
    userService = mock(IUserService.class);
    messagingService = mock(IMessagingService.class);
    service = new NotificationApplicationApplicationService(repository, userService,
        messagingService);
  }

  @Test
  void testSaveAndStreamNotification_whenEverythingIsOk_success() {
    doNothing().when(repository).save(any(Notification.class));
    when(userService.getUsersByCategory(any(NotificationCategory.class))).thenReturn(
        List.of(mock(UserDTO.class)));
    doNothing().when(messagingService).notifyUsers(any(Notification.class), anyList());

    var notification =
        service.saveAndStreamNotification();
    assertNotNull(notification);

    verify(repository).save(any(Notification.class));
    verify(userService).getUsersByCategory(any(NotificationCategory.class));
    verify(messagingService).notifyUsers(any(Notification.class), any());
  }

  @Test
  void testSaveAndStreamNotification_whenNotSubscribedUsers_success() {
    doNothing().when(repository).save(any(Notification.class));
    when(userService.getUsersByCategory(any(NotificationCategory.class))).thenReturn(
        Collections.emptyList());

    var notification =
        service.saveAndStreamNotification();
    assertNotNull(notification);

    verify(repository).save(any(Notification.class));
    verify(userService).getUsersByCategory(any(NotificationCategory.class));
    verify(messagingService, never()).notifyUsers(any(Notification.class), any());
  }

  @Test
  void testSaveAndStreamNotification_whenAlreadyExists_error() {
    when(repository.existsByCategoryChecksum(any(NotificationCategory.class),
        anyString())).thenReturn(true);

    assertThrows(NotificationError.class, () ->
        service.saveAndStreamNotification());

    verify(repository, never()).save(any());
    verify(userService, never()).getUsersByCategory(any());
    verify(messagingService, never()).notifyUsers(any(), any());
  }

}