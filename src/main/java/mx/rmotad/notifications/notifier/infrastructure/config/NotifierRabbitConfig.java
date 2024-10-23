package mx.rmotad.notifications.notifier.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotifierRabbitConfig {

  public static final String NOTIFIER_DELIVERED_CREATED_QUEUE = "notification-delivered";
  public static final String EXCHANGE = "notifications-exchange";

  @Bean
  public RabbitTemplate notifierRabbitTemplate(ConnectionFactory connectionFactory) {
    return new RabbitTemplate(connectionFactory);
  }

  @Bean
  public FanoutExchange notifierFanoutExchange() {
    return new FanoutExchange(EXCHANGE, true, false);
  }

  @Bean
  public Queue notifierCreateNotificationQueue() {
    return new Queue(NOTIFIER_DELIVERED_CREATED_QUEUE, false);
  }

  @Bean
  public Binding notifierCreatedNotificationBinding(FanoutExchange notifierFanoutExchange,
      Queue notifierCreateNotificationQueue) {
    return BindingBuilder.bind(notifierCreateNotificationQueue).to(notifierFanoutExchange);
  }
}