package com.jeffrey.paymentconsumer.application.usecase;

import com.jeffrey.commondto.rabbitmq.dto.PaymentMessage;

public interface PaymentUseCase {

    void processed(PaymentMessage paymentMessage);

}
