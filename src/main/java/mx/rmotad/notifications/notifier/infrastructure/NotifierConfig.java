package mx.rmotad.notifications.notifier.infrastructure;

import mx.rmotad.notifications.notifier.domain.Notifier;
import mx.rmotad.notifications.notifier.domain.NotifierFactory;
import mx.rmotad.notifications.notifier.infrastructure.messaging.EmailNotifier;
import mx.rmotad.notifications.notifier.infrastructure.messaging.PushNotifier;
import mx.rmotad.notifications.notifier.infrastructure.messaging.SmsNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotifierConfig{

  @Bean
  public NotifierFactory notifierFactory() {
    return (channel) ->
        switch (channel){
        case PUSH -> pushNotifier();
        case EMAIL -> emailNotifier();
        case SMS -> smsNotifier();
    };
  }

  @Bean
  public Notifier emailNotifier(){
    return new EmailNotifier();
  }

  @Bean
  public Notifier smsNotifier(){
    return new SmsNotifier();
  }

  @Bean
  public Notifier pushNotifier(){
    return new PushNotifier();
  }

}
