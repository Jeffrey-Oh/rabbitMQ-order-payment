package com.jeffrey.orderconsumer.application.usecase;

import com.jeffrey.commondto.rabbitmq.dto.OrderMessage;

import java.util.UUID;

public interface OrderUseCase {

    void confirm(OrderMessage orderMessage);

    void completed(Long orderId, UUID transactionId);

}
