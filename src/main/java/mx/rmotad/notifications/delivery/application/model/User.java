package mx.rmotad.notifications.delivery.application.model;

import java.util.List;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationCategory;
import mx.rmotad.notifications.delivery.domain.model.DeliveryNotificationChannel;

/**
 * This record is used to hold user api responses
 *
 * @param id               user id
 * @param name             user name
 * @param email            user email
 * @param phone            user phone
 * @param subscriptionList user category subscription list
 * @param channels         user available channel to be notified
 */
public record User(String id, String name, String email, String phone,
                   List<DeliveryNotificationCategory> subscriptionList,
                   List<DeliveryNotificationChannel> channels) {

}