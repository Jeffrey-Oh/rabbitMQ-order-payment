package com.jeffrey.commondto.rabbitmq.dto;

import com.jeffrey.common.enums.PaymentMethod;

public record PaymentMessage(
    Long orderId,
    PaymentMethod paymentMethod,
    int totalAmount
) {
    public static PaymentMessage from(Long orderId, PaymentMethod paymentMethod, int totalAmount) {
        return new PaymentMessage(orderId, paymentMethod, totalAmount);
    }
}
