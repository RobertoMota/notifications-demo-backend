package mx.rmotad.notifications.delivery.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import java.time.Instant;
import java.util.List;
import lombok.experimental.UtilityClass;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationChannel;

/**
 * This class creates new Delivery objects
 */
@UtilityClass
public class DeliveryFactory {

  /**
   * Creates a new Delivery, setting id and creation date
   *
   * @param notificationId the id of the notification
   * @param channel        the channel used to notify
   * @param destinations   the notified subscribers
   * @param result         new Delivery object
   * @return
   */
  public static Delivery create(String notificationId, DeliveryNotificationChannel channel,
      List<String> destinations, boolean result) {
    return new Delivery(UlidCreator.getUlid().toLowerCase(), notificationId, channel, destinations,
        result,
        Instant.now());
  }
}
