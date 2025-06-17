package com.jeffrey.paymentconsumer.adapter.inbound.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffrey.commondto.rabbitmq.dto.PaymentMessage;
import com.jeffrey.paymentconsumer.application.usecase.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompletePaymentConsumer {

    private final ObjectMapper objectMapper;
    private final PaymentUseCase paymentUseCase;

    @SneakyThrows
    @RabbitListener(queues = "payment.processed.queue")
    public void processedPaymentListener(String message) {
        if (message == null || message.isEmpty()) {
            return; // 메시지가 비어있으면 무시
        }

        PaymentMessage paymentMessage = objectMapper.readValue(message, PaymentMessage.class);
        log.info("📥 Received payment message: {}", paymentMessage);

        paymentUseCase.processed(paymentMessage);
    }

}
