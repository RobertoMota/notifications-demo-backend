package mx.rmotad.notifications.notification.infraestructure.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationRabbitConfig {

  public static final String EXCHANGE = "notifications-exchange";

  @Bean
  public RabbitTemplate notificationRabbitTemplate(ConnectionFactory connectionFactory) {
    return new RabbitTemplate(connectionFactory);
  }

  @Bean
  public FanoutExchange notificationFanoutExchange() {
    return new FanoutExchange(EXCHANGE, true, false);
  }

}