package mx.rmotad.notifications.notification.infraestructure.config;

import java.security.MessageDigest;
import mx.rmotad.notifications.notification.domain.HashGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashGeneratorConfig {

  @Bean
  public HashGenerator hashGenerator() {
    return (string ->
        new String(MessageDigest.getInstance("MD5").digest(string.getBytes()))
    );
  }
}
