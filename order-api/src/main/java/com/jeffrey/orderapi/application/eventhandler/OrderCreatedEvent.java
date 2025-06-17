package com.jeffrey.orderapi.application.eventhandler;

import com.jeffrey.common.enums.PaymentMethod;
import com.jeffrey.domain.Order;

public record OrderCreatedEvent(Order order, PaymentMethod paymentMethod) {
}
