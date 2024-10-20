package mx.rmotad.notifications.notification.notification.application.dto;

public record UserDTO(String id, String name, String email, String phone, String[] subscriptionList,
                      String[] channels) {

}