package mx.rmotad.notifications.notification.notification.application;

import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.notification.notification.application.service.IMessagingService;
import mx.rmotad.notifications.notification.notification.application.service.IUserService;
import mx.rmotad.notifications.notification.notification.model.Notification;
import mx.rmotad.notifications.notification.notification.model.NotificationCategory;
import mx.rmotad.notifications.notification.notification.model.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationApplicationApplicationService implements INotificationApplicationService {

  private final NotificationRepository repository;
  private final IUserService userService;
  private final IMessagingService messagingService;

  @Override
  public Notification saveAndStreamNotification() {
    Notification notification = Notification.create(NotificationCategory.SPORTS, "", repository);
    repository.save(notification);
    var users = userService.getUsersByCategory(NotificationCategory.SPORTS);

    if (users.isEmpty()) {
      return notification;
    }
    messagingService.notifyUsers(notification, users);

    return notification;
  }
}
