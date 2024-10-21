package mx.rmotad.notifications.notification.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.common.enums.NotificationCategory;
import mx.rmotad.notifications.common.enums.NotificationChannel;
import mx.rmotad.notifications.notification.application.dto.UserDTO;
import mx.rmotad.notifications.notification.application.service.INotificationProducerFactory;
import mx.rmotad.notifications.notification.application.service.IUserService;
import mx.rmotad.notifications.notification.domain.HashBuilder;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;
import mx.rmotad.notifications.notification.domain.NotificationFactory;
import mx.rmotad.notifications.notification.domain.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseNotificationUseCases implements NotificationUseCases {

  private final NotificationRepository repository;
  private final HashBuilder hashBuilder;
  private final IUserService userService;
  private final INotificationProducerFactory producerFactory;

  @Override
  public NotificationDomain newNotificationUseCase(NotificationCategory category, String message) {

    final NotificationDomain notification =
        NotificationFactory.create(category, message, repository, hashBuilder);

    repository.save(notification);

    List<UserDTO> subscribedUsers = userService.getUsersByCategory(category);

    if (subscribedUsers.isEmpty()) {
      return notification;
    }

    segmentUsersByChannel(subscribedUsers).forEach(
        (channel, userList) ->
            producerFactory.getProducerForChannel(channel).notifyUsers(notification, userList));
    return notification;
  }

  private static Map<NotificationChannel, List<UserDTO>> segmentUsersByChannel(
      List<UserDTO> subscribedUsers) {
    Map<NotificationChannel, List<UserDTO>> segmentedUsers = new HashMap<>();
    subscribedUsers.forEach(userDTO -> {
      userDTO.channels().forEach(channel -> {
        segmentedUsers.putIfAbsent(channel, new ArrayList<>());
        segmentedUsers.get(channel).add(userDTO);
      });
    });
    return segmentedUsers;
  }
}
