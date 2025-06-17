package com.jeffrey.orderapi.application.port.out;

import com.jeffrey.orderapi.application.dto.OrderMessage;

public interface MessagePublisher {

    void publishOrderCreated(OrderMessage message);

}
