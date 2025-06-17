package com.jeffrey.orderconsumer.adapter.inbound.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffrey.commondto.rabbitmq.dto.CompletedPaymentMessage;
import com.jeffrey.orderconsumer.application.usecase.OrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompletedPaymentConsumer {

    private final ObjectMapper objectMapper;
    private final OrderUseCase orderUseCase;

    @SneakyThrows
    @RabbitListener(queues = "payment.completed.queue")
    public void completedPaymentListener(String message) {
        if (message == null || message.isEmpty()) {
            return; // 메시지가 비어있으면 무시
        }

        CompletedPaymentMessage completedPaymentMessage = objectMapper.readValue(message, CompletedPaymentMessage.class);
        log.info("📥 Received payment completed message: {}", completedPaymentMessage);

        orderUseCase.completed(completedPaymentMessage.orderId(), completedPaymentMessage.transactionId());
    }

}
