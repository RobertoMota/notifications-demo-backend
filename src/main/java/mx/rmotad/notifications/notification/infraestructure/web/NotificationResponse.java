package mx.rmotad.notifications.notification.infraestructure.web;

import mx.rmotad.notifications.notification.domain.model.IdentifiableResource;

public record NotificationResponse(String id) implements IdentifiableResource {

}
