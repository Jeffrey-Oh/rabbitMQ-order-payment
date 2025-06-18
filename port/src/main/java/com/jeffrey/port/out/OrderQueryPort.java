package com.jeffrey.port.out;

import com.jeffrey.domain.Order;

public interface OrderQueryPort {

    Order lockFindByOrderId(Long orderId);
    Order findByOrderId(Long orderId);

}
