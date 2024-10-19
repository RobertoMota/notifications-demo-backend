package mx.rmotad.notifications.users.domain;

public record User(String id, String name, String email, String phone, String[] subscriptionList,
                   String[] channels) {

}