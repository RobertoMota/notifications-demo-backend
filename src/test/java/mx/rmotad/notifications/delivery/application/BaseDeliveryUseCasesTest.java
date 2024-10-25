package mx.rmotad.notifications.delivery.application;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import mx.rmotad.notifications.delivery.application.model.Notification;
import mx.rmotad.notifications.delivery.application.model.User;
import mx.rmotad.notifications.delivery.application.service.UserService;
import mx.rmotad.notifications.delivery.domain.DeliveryFactory;
import mx.rmotad.notifications.delivery.domain.DeliveryRepository;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationChannel;
import mx.rmotad.notifications.delivery.domain.model.Notifier;
import mx.rmotad.notifications.delivery.domain.model.NotifierFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

class BaseDeliveryUseCasesTest {

  private DeliveryUseCases useCases;
  private UserService userService;
  private NotifierFactory notifierFactory;
  private DeliveryRepository deliveryRepository;
  @Spy
  DeliveryFactory deliveryFactory;

  @BeforeEach
  public void setUp() {
    userService = mock(UserService.class);
    notifierFactory = mock(NotifierFactory.class);
    deliveryRepository = mock(DeliveryRepository.class);
    useCases = new BaseDeliveryUseCases(userService, notifierFactory, deliveryRepository);
  }

  @Test
  void deliveryNotifications_success() {
    when(userService.getUsersByCategory(any())).thenReturn(
        Collections.singletonMap(DeliveryNotificationChannel.SMS,
            Collections.singletonList(mock(User.class))));
    Notifier notifier = mock(Notifier.class);
    when(notifierFactory.getNotifier(any())).thenReturn(notifier);
    when(notifier.notify(any(), any())).thenReturn(List.of("destination1"));
    assertAll(() ->
        useCases.deliveryNotifications(mock(Notification.class)));
    verify(notifier).notify(any(), any());
    verify(deliveryRepository).save(any());
  }

  @Test
  void deliveryNotifications_subscribersEmpty_success() {
    when(userService.getUsersByCategory(any())).thenReturn(
        Collections.singletonMap(DeliveryNotificationChannel.SMS,
            Collections.emptyList()));
    Notifier notifier = mock(Notifier.class);
    when(notifierFactory.getNotifier(any())).thenReturn(notifier);
    when(notifier.notify(any(), any())).thenReturn(List.of("destination1"));
    assertAll(() ->
        useCases.deliveryNotifications(mock(Notification.class)));
    verify(notifier).notify(any(), any());
    verify(deliveryRepository).save(any());
  }

  @Test
  void deliveryNotifications_whenNullNotifier_success() {
    when(userService.getUsersByCategory(any())).thenReturn(Collections.singletonMap(
        DeliveryNotificationChannel.SMS, Collections.singletonList(mock(User.class))));
    when(notifierFactory.getNotifier(any())).thenReturn(null);
    assertAll(() ->
        useCases.deliveryNotifications(mock(Notification.class)));
  }

  @Test
  void deliveryNotifications_whenNoSubscribers_Success() {
    when(userService.getUsersByCategory(any())).thenReturn(Collections.singletonMap(
        DeliveryNotificationChannel.SMS, Collections.emptyList()));
    assertAll(() ->
        useCases.deliveryNotifications(mock(Notification.class)));
  }
}