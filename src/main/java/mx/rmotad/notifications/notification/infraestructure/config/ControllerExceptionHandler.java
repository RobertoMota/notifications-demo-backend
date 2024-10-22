package mx.rmotad.notifications.notification.infraestructure.config;

import io.vavr.control.Try;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import mx.rmotad.notifications.notification.domain.error.NotificationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

@ControllerAdvice
public class ControllerExceptionHandler {

  public static final String MESSAGES_RESOURCE = "messages";
  public static final String VALIDATION_ERROR_CODE = "N-0002";
  public static final String ERROR_MESSAGES_DELIMITER = "; ";
  private final Locale locale = Locale.getDefault();

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(NotificationError.class)
  ResponseEntity<ErrorResponse> handleDomainException(NotificationError error) {
    return buildResponse(ResponseEntity.badRequest(), error.getMessage());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(InternalServerError.class)
  ResponseEntity<ErrorResponse> handleInternalServerException(NotificationError error) {
    return buildResponse(ResponseEntity.badRequest(), error.getMessage());
  }

  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(BindException ex) {
    String errors = ex.getFieldErrors().stream()
        .map(error -> error.getField() + " " + error.getDefaultMessage()).collect(
            Collectors.joining(ERROR_MESSAGES_DELIMITER));

    return buildErrorResponse(ResponseEntity.badRequest(), VALIDATION_ERROR_CODE, errors);
  }

  private String getMessage(String code) {

    return Try.of(
            () -> ResourceBundle.getBundle(MESSAGES_RESOURCE, locale))
        .map(bundle -> bundle.getString(code))
        .recover(ex -> code).get();
  }

  private ResponseEntity<ErrorResponse> buildResponse(BodyBuilder bodyBuilder, String code) {
    return buildErrorResponse(bodyBuilder, code, getMessage(code));
  }

  private ResponseEntity<ErrorResponse> buildErrorResponse(BodyBuilder bodyBuilder, String code, String detail) {
    return bodyBuilder.body(new ErrorResponse(code, detail));
  }

  public record ErrorResponse(String code, String details) {

  }
}
