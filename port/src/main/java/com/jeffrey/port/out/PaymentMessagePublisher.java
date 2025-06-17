package com.jeffrey.port.out;

import com.jeffrey.commondto.rabbitmq.dto.CompletedPaymentMessage;
import com.jeffrey.commondto.rabbitmq.dto.PaymentMessage;

public interface PaymentMessagePublisher {

    void publishPayment(PaymentMessage message);
    void publishPaymentCompleted(CompletedPaymentMessage completedPaymentMessage);

}
