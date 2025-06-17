package com.jeffrey.orderapi.adapter.outbound.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffrey.orderapi.application.dto.OrderMessage;
import com.jeffrey.orderapi.application.port.out.MessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMessagePublisher implements MessagePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void publishOrderCreated(OrderMessage message) {
        rabbitTemplate.convertAndSend(
            "orders.exchange",
            "order.created",
            objectMapper.writeValueAsString(message)
        );
    }
}
