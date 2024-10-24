package mx.rmotad.notifications.notification.infraestructure.config;

import java.security.MessageDigest;
import mx.rmotad.notifications.notification.domain.HashGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashGeneratorConfig {

  public static final String ALG_SHA_256 = "SHA256";

  /**
   * Generates hash value using the sha256 algorithm
   *
   * @return the hashed value
   */
  @Bean
  public HashGenerator hashGenerator() {
    return (string ->
        new String(MessageDigest.getInstance(ALG_SHA_256).digest(string.getBytes()))
    );
  }
}
