package mx.rmotad.notifications.notification.application;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.common.enums.NotificationCategory;
import mx.rmotad.notifications.notification.application.service.IUserService;
import mx.rmotad.notifications.notification.application.service.NotificationProducer;
import mx.rmotad.notifications.notification.domain.HashGenerator;
import mx.rmotad.notifications.notification.domain.NotificationFactory;
import mx.rmotad.notifications.notification.domain.NotificationRepository;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseNotificationUseCases implements NotificationUseCases {

  private final NotificationRepository notificationRepository;
  private final HashGenerator hashGenerator;
  private final IUserService userService;
  private final NotificationProducer notificationProducer;

  @Override
  public NotificationDomain newNotificationUseCase(NotificationCategory category, String message) {

    return Try.of(
            () -> NotificationFactory.create(category, message, notificationRepository, hashGenerator))
        .andThen(notificationRepository::save)
        .andThen(notificationProducer::notifyCreated)
        .get()
        ;
//
//    final NotificationDomain notification =
//        NotificationFactory.create(category, message, repository, hashGenerator);
//
//    repository.save(notification);
//
//    List<UserDTO> subscribedUsers = userService.getUsersByCategory(category);

//    if (subscribedUsers.isEmpty()) {
//      return notification;
//    }
//
//    segmentUsersByChannel(subscribedUsers).forEach(
//        (channel, userList) ->
//            pro.getProducerForChannel(channel).notifyNotificationCreated(notification));
//    return notification;
  }
//
//  private static Map<NotificationChannel, List<UserDTO>> segmentUsersByChannel(
//      List<UserDTO> subscribedUsers) {
//    Map<NotificationChannel, List<UserDTO>> segmentedUsers = new HashMap<>();
//    subscribedUsers.forEach(userDTO -> {
//      userDTO.channels().forEach(channel -> {
//        segmentedUsers.putIfAbsent(channel, new ArrayList<>());
//        segmentedUsers.get(channel).add(userDTO);
//      });
//    });
//    return segmentedUsers;
//  }
}
