package mx.rmotad.notifications.notification.notification.application.service;

import java.util.List;
import mx.rmotad.notifications.notification.notification.application.dto.UserDTO;
import mx.rmotad.notifications.notification.notification.model.NotificationCategory;

public interface IUserService {

  List<UserDTO> getUsersByCategory(NotificationCategory category);
}
