package mx.rmotad.notifications.notification.infraestructure.producer;

import java.util.List;
import mx.rmotad.notifications.notification.application.dto.UserDTO;
import mx.rmotad.notifications.notification.application.service.NotificationProducer;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;
import org.springframework.stereotype.Component;

@Component
public class PushNotificationProducer implements NotificationProducer {

  @Override
  public void notifyUsers(NotificationDomain notification, List<UserDTO> users) {
    System.out.println("send by push to users");
  }
}
