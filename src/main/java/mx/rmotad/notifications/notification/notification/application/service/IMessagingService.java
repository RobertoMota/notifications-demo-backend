package mx.rmotad.notifications.notification.notification.application.service;

import java.util.List;
import mx.rmotad.notifications.notification.notification.application.dto.UserDTO;
import mx.rmotad.notifications.notification.notification.model.Notification;

public interface IMessagingService {

  void notifyUsers(Notification notification, List<UserDTO> users);
}
