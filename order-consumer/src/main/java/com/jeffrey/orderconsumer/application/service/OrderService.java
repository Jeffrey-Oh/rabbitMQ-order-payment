package com.jeffrey.orderconsumer.application.service;

import com.jeffrey.commondto.rabbitmq.dto.OrderMessage;
import com.jeffrey.commondto.rabbitmq.dto.PaymentMessage;
import com.jeffrey.domain.Order;
import com.jeffrey.orderconsumer.application.usecase.OrderUseCase;
import com.jeffrey.port.out.OrderCommandPort;
import com.jeffrey.port.out.OrderQueryPort;
import com.jeffrey.port.out.PaymentMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {

    private final OrderCommandPort orderCommandPort;
    private final OrderQueryPort orderQueryPort;
    private final PaymentMessagePublisher paymentMessagePublisher;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirm(OrderMessage orderMessage) {
        // 1. 주문 확인
        Order order = orderQueryPort.lockFindByOrderId(orderMessage.orderId());
        if (order == null) {
            throw new IllegalArgumentException("Order not found: " + orderMessage.orderId());
        }

        // 2. 주문 상태 확인으로 업데이트
        order.confirm();
        orderCommandPort.save(order);

        // 3. 결제 메시지 발행
        paymentMessagePublisher.publishPayment(
            new PaymentMessage(
                orderMessage.orderId(),
                orderMessage.paymentMethod(),
                orderMessage.totalAmount()
            )
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void completed(Long orderId, UUID transactionId) {
        // 결제 트랜잭션 ID 로깅
        log.info("📥 Completed payment for order ID: {}, transaction ID: {}", orderId, transactionId);

        // 1. 주문 조회
        Order order = orderQueryPort.lockFindByOrderId(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found: " + orderId);
        }

        // 2. 주문 상태 완료로 업데이트
        order.completed();
        orderCommandPort.save(order);
    }

}
