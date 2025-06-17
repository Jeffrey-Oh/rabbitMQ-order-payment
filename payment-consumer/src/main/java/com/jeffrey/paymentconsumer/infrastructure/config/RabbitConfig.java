package com.jeffrey.paymentconsumer.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

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

