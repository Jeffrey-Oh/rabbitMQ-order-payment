package com.jeffrey.orderconsumer.infrastructure.config;

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

    @Bean
    public Exchange paymentExchange() {
        return ExchangeBuilder.topicExchange("payment.exchange").durable(true).build();
    }

    @Bean
    public Queue paymentProcessedQueue() {
        return QueueBuilder.durable("payment.processed.queue").build();
    }

    @Bean
    public Binding bindPaymentProcessed(Queue paymentProcessedQueue, Exchange paymentExchange) {
        return BindingBuilder
            .bind(paymentProcessedQueue)
            .to(paymentExchange)
            .with("payment.processed")
            .noargs();
    }

    @Bean
    public Queue paymentCompletedQueue() {
        return QueueBuilder.durable("payment.completed.queue").build();
    }

    @Bean
    public Binding bindPaymentCompleted(Queue paymentCompletedQueue, Exchange paymentExchange) {
        return BindingBuilder
            .bind(paymentCompletedQueue)
            .to(paymentExchange)
            .with("payment.completed")
            .noargs();
    }

}

