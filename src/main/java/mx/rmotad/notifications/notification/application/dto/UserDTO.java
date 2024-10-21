package mx.rmotad.notifications.notification.application.dto;

import java.util.List;
import mx.rmotad.notifications.common.enums.NotificationCategory;
import mx.rmotad.notifications.common.enums.NotificationChannel;

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
public record UserDTO(String id, String name, String email, String phone,
                      List<NotificationCategory> subscriptionList,
                      List<NotificationChannel> channels) {

}