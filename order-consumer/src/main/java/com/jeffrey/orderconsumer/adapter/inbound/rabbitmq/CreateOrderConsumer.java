package com.jeffrey.orderconsumer.adapter.inbound.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffrey.commondto.rabbitmq.dto.OrderMessage;
import com.jeffrey.orderconsumer.application.usecase.OrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateOrderConsumer {

    private final ObjectMapper objectMapper;
    private final OrderUseCase orderUseCase;

    @SneakyThrows
    @RabbitListener(queues = "order.created.queue")
    public void createOrderListener(String message) {
        if (message == null || message.isEmpty()) {
            return; // 메시지가 비어있으면 무시
        }

        OrderMessage orderMessage = objectMapper.readValue(message, OrderMessage.class);
        log.info("📥 Received order message: {}", orderMessage);

        orderUseCase.confirm(orderMessage);
    }

}
