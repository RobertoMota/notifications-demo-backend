package mx.rmotad.notifications.notifier.application.model;

import java.time.Instant;
import mx.rmotad.notifications.notifier.domain.model.NotifierCategory;

public record Notification(String id, NotifierCategory category, String content,
                           String hash, Instant createdAt) {

}