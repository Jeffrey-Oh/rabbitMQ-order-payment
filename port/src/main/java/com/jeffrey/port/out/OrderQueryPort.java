package com.jeffrey.port.out;

import com.jeffrey.domain.Order;

public interface OrderQueryPort {

    Order findByOrderId(Long orderId);

}
