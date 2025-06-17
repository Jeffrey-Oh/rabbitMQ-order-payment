package com.jeffrey.orderapi.application.eventhandler;

import com.jeffrey.commondto.rabbitmq.dto.OrderMessage;
import com.jeffrey.domain.Order;
import com.jeffrey.port.out.OrderMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventHandler {

    private final OrderMessagePublisher orderMessagePublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(OrderCreatedEvent event) {
        Order order = event.order();
        orderMessagePublisher.publishOrderCreated(OrderMessage.from(order, event.paymentMethod()));
    }
}

