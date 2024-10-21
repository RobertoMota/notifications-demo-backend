package mx.rmotad.notifications.users.domain;

import mx.rmotad.notifications.common.enums.NotificationCategory;
import mx.rmotad.notifications.common.enums.NotificationChannel;

public record User(String id, String name, String email, String phone,
                   NotificationCategory[] subscriptionList,
                   NotificationChannel[] channels) {

}