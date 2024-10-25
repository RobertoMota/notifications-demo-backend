package mx.rmotad.notifications.delivery.infrastructure.config;

import mx.rmotad.notifications.delivery.domain.model.Notifier;
import mx.rmotad.notifications.delivery.domain.model.NotifierFactory;
import mx.rmotad.notifications.delivery.infrastructure.httpclient.EmailNotifier;
import mx.rmotad.notifications.delivery.infrastructure.httpclient.PushNotifier;
import mx.rmotad.notifications.delivery.infrastructure.httpclient.SmsNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotifierConfig {

  /**
   * Retrieves the correct notifier for the given channel
   * @return
   */
  @Bean
  public NotifierFactory notifierFactory() {
    return (channel) ->
        switch (channel) {
          case PUSH -> pushNotifier();
          case EMAIL -> emailNotifier();
          case SMS -> smsNotifier();
        };
  }

  @Bean
  public Notifier emailNotifier() {
    return new EmailNotifier();
  }

  @Bean
  public Notifier smsNotifier() {
    return new SmsNotifier();
  }

  @Bean
  public Notifier pushNotifier() {
    return new PushNotifier();
  }

}
