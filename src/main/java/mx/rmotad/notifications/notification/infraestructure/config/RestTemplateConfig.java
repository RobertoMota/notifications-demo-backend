package mx.rmotad.notifications.notification.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Holds the implementation of the RestTemplates
 */
@Configuration
public class RestTemplateConfig {

  /**
   * Rest template for communicate with user API
   *
   * @return a new RestTemplate
   */
  @Bean
  public RestTemplate usersRestTemplate() {
    return new RestTemplate();
  }
}
