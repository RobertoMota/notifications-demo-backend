package mx.rmotad.notifications.notification.infraestructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "")
public class RabbitConfig {

  public static final String NOTIFICATION_CREATED_QUEUE = "notification-created";
  public static final String EXCHANGE = "notifications-exchange";

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    return new RabbitTemplate(connectionFactory);
  }

  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange(EXCHANGE, true, false);
  }

  @Bean
  public Queue createdNotificationQueue() {
    return new Queue(NOTIFICATION_CREATED_QUEUE, false);
  }

  @Bean
  public Binding createNotificationBinding(FanoutExchange fanoutExchange, Queue myQueue){
    return BindingBuilder.bind(myQueue).to(fanoutExchange);
  }
}