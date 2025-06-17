package com.jeffrey.messagerabbitmq.adapter.outbound.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffrey.commondto.rabbitmq.dto.OrderMessage;
import com.jeffrey.port.out.OrderMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMessagePublisherImpl implements OrderMessagePublisher {

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
