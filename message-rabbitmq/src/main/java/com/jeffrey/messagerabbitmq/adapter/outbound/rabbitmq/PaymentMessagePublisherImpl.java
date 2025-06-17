package com.jeffrey.messagerabbitmq.adapter.outbound.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffrey.commondto.rabbitmq.dto.CompletedPaymentMessage;
import com.jeffrey.commondto.rabbitmq.dto.PaymentMessage;
import com.jeffrey.port.out.PaymentMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMessagePublisherImpl implements PaymentMessagePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void publishPayment(PaymentMessage message) {
        rabbitTemplate.convertAndSend(
            "payment.exchange",
            "payment.processed",
            objectMapper.writeValueAsString(message)
        );
    }

    @SneakyThrows
    @Override
    public void publishPaymentCompleted(CompletedPaymentMessage completedPaymentMessage) {
        rabbitTemplate.convertAndSend(
            "payment.exchange",
            "payment.completed",
            objectMapper.writeValueAsString(completedPaymentMessage)
        );
    }

}
