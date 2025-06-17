package com.jeffrey.orderapi.application.usecase;

import com.jeffrey.orderapi.application.usecase.command.CreateOrderCommand;

public interface OrderUseCase {

    Long createOrder(CreateOrderCommand command);

}
