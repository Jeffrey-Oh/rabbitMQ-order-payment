package com.jeffrey.orderapi.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Exchange orderExchange() {
        return ExchangeBuilder.topicExchange("orders.exchange").durable(true).build();
    }

    @Bean
    public Queue orderCreatedQueue() {
        return QueueBuilder.durable("order.created.queue").build();
    }

    @Bean
    public Binding bindOrderCreated(Queue orderCreatedQueue, Exchange orderExchange) {
        return BindingBuilder
            .bind(orderCreatedQueue)
            .to(orderExchange)
            .with("order.created")
            .noargs();
    }
}

