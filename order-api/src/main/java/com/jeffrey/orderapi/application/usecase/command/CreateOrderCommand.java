package com.jeffrey.orderapi.application.usecase.command;

import com.jeffrey.common.enums.PaymentMethod;

import java.util.List;

public record CreateOrderCommand(Long userId, List<OrderItemCommand> items, PaymentMethod paymentMethod) {
    public record OrderItemCommand(Long menuId, int quantity) {}
}
