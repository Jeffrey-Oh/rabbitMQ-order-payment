package com.jeffrey.orderapi.application.usecase;

import com.jeffrey.common.enums.OrderStatus;
import com.jeffrey.orderapi.application.usecase.command.CreateOrderCommand;

public interface OrderUseCase {

    Long createOrder(CreateOrderCommand command);

    OrderStatus pollingOrderStatus(Long userId, Long orderId);

}
