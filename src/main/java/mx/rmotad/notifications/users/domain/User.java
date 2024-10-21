package mx.rmotad.notifications.users.domain;

import java.util.List;

public record User(String id, String name, String email, String phone,
                   List<Category> subscriptionList, List<Channel> channels) {

}