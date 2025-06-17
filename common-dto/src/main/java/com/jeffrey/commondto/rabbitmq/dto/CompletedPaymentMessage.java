package com.jeffrey.commondto.rabbitmq.dto;

import java.util.UUID;

public record CompletedPaymentMessage(
    Long orderId,
    UUID transactionId
) {
    public static CompletedPaymentMessage from(Long orderId) {
        return new CompletedPaymentMessage(orderId, UUID.randomUUID());
    }
}
