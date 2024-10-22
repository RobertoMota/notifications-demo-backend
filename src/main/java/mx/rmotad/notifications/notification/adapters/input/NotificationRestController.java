package mx.rmotad.notifications.notification.adapters.input;

import static org.springframework.http.HttpStatus.CREATED;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.notification.application.NotificationUseCases;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;
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
  NotificationResponse createNotification(@Valid @RequestBody NotificationRequest notificationRequest) {
    NotificationDomain createdNotification = notificationUseCases.newNotificationUseCase(
        notificationRequest.category(),
        notificationRequest.content());

    return new NotificationResponse(createdNotification.id());
  }
}
