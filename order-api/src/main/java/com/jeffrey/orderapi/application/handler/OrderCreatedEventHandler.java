package com.jeffrey.orderapi.application.handler;

import com.jeffrey.domain.Order;
import com.jeffrey.orderapi.application.dto.OrderMessage;
import com.jeffrey.orderapi.application.port.out.MessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventHandler {

    private final MessagePublisher messagePublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(OrderCreatedEvent event) {
        Order order = event.order();
        messagePublisher.publishOrderCreated(OrderMessage.from(order, event.paymentMethod()));
    }
}

