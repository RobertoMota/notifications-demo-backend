package mx.rmotad.notifications.notification.adapters.input;

import static org.springframework.http.HttpStatus.CREATED;

import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.notification.application.NotificationUseCases;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notifications")
@RequiredArgsConstructor
public class NotificationRestController {

  private final NotificationUseCases notificationUseCases;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(CREATED)
  NotificationResponse createNotification(@RequestBody NotificationRequest notificationRequest) {
    var r = notificationUseCases.newNotificationUseCase(
        notificationRequest.category(),
        notificationRequest.message());

    return new NotificationResponse(r.id());
  }
}
