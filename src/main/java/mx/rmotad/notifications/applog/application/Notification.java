package mx.rmotad.notifications.applog.application;

import java.time.Instant;

public record Notification(String id, String content, String hash, Instant createdAt) {

}
