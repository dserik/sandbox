package org.test.springsandbox.config.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestMessageReceiverConfig {

  @Bean
  public DirectExchange updateDeliveryExchange() {
    return new DirectExchange(
        "receiver.exchange", true, false);
  }

  @Bean
  public Queue updateDeliveryQueue() {
    return QueueBuilder.durable("receiver.queue")
        .withArgument("x-message-ttl", 3000000).build();
  }

  @Bean
  public Binding bindUpdateDeliveryExchange() {
    return BindingBuilder.bind(updateDeliveryQueue())
        .to(updateDeliveryExchange()).with("receiver.key");
  }
}
