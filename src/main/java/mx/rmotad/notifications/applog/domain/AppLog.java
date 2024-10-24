package mx.rmotad.notifications.applog.domain;

import java.time.Instant;
import java.util.List;

public record AppLog(String id, String notificationId, Instant createdAd,
                     List<NotificationDelivery> deliveryList) {

}
