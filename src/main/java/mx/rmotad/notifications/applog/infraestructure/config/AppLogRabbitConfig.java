package mx.rmotad.notifications.applog.infraestructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppLogRabbitConfig {

  public static final String NOTIFICATION_CREATED_QUEUE = "notification-created";
  public static final String EXCHANGE = "notifications-exchange";

  @Bean
  public RabbitTemplate appLogRabbitTemplate(ConnectionFactory connectionFactory) {
    return new RabbitTemplate(connectionFactory);
  }

  @Bean
  public FanoutExchange appLogFanoutExchange() {
    return new FanoutExchange(EXCHANGE, true, false);
  }

  @Bean
  public Queue appLogCreateNotificationQueue() {
    return new Queue(NOTIFICATION_CREATED_QUEUE, false);
  }

  @Bean
  public Binding createNotificationBinding(FanoutExchange appLogFanoutExchange,
      Queue appLogCreateNotificationQueue) {
    return BindingBuilder.bind(appLogCreateNotificationQueue).to(appLogFanoutExchange);
  }
}