package com.jeffrey.port.out;

import com.jeffrey.commondto.rabbitmq.dto.OrderMessage;

public interface OrderMessagePublisher {

    void publishOrderCreated(OrderMessage message);

}
