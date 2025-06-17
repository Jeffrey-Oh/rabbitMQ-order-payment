package com.jeffrey.orderapi.application.handler;

import com.jeffrey.common.PaymentMethod;
import com.jeffrey.domain.Order;

public record OrderCreatedEvent(Order order, PaymentMethod paymentMethod) {
}
