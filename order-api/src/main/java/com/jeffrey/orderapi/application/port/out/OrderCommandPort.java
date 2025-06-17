package com.jeffrey.orderapi.application.port.out;

import com.jeffrey.domain.Order;

public interface OrderCommandPort {

    Order save(Order order);

}
