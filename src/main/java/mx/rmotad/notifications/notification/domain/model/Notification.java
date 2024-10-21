package mx.rmotad.notifications.notification.domain.model;

import mx.rmotad.notifications.common.enums.NotificationCategory;

public interface Notification {

  NotificationCategory category();

  String content();

}
