package mx.rmotad.notifications.delivery.domain.model;

@FunctionalInterface
public interface NotifierFactory {

  Notifier getNotifier(DeliveryNotificationChannel channel);
}
