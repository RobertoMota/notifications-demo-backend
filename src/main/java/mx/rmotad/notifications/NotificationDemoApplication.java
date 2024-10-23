package mx.rmotad.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "mx.rmotad.notifications")
public class NotificationDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotificationDemoApplication.class, args);
  }

}
