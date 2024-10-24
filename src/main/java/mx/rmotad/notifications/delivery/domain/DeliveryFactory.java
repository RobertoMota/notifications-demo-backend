package mx.rmotad.notifications.delivery.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import java.time.Instant;
import java.util.List;
import lombok.experimental.UtilityClass;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationChannel;

@UtilityClass
public class DeliveryFactory {

  public static Delivery create(String notificationId, DeliveryNotificationChannel channel,
      List<String> destinations, boolean result) {
    return new Delivery(UlidCreator.getUlid().toLowerCase(), notificationId, channel, destinations,
        result,
        Instant.now());
  }
}
