package mx.rmotad.notifications.notification.infraestructure.producer;

import mx.rmotad.notifications.common.enums.NotificationChannel;
import mx.rmotad.notifications.notification.application.service.INotificationProducerFactory;
import mx.rmotad.notifications.notification.application.service.NotificationProducer;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducerFactory implements INotificationProducerFactory {

  @Override
  public NotificationProducer getProducerForChannel(NotificationChannel channel) {
    return switch (channel) {
      case SMS -> new SmsNotificationProducer();
      case EMAIL -> new EmailNotificationProducer();
      case PUSH -> new PushNotificationProducer();
    };
  }
}
