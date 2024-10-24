package mx.rmotad.notifications.applog.domain;

import java.time.Instant;

public record NotificationDelivery(String channel, String userIds, Instant deliveredAt) {

}
