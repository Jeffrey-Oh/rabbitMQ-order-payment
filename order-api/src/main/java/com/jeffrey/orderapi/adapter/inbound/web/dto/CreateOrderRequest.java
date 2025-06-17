package com.jeffrey.orderapi.adapter.inbound.web.dto;

import com.jeffrey.common.PaymentMethod;
import com.jeffrey.orderapi.application.usecase.command.CreateOrderCommand;

import java.util.List;

public record CreateOrderRequest(List<ItemDto> items, PaymentMethod paymentMethod) {
    public CreateOrderCommand toCommand(Long userId) {
        return new CreateOrderCommand(
            userId, items().stream()
                .map(i -> new CreateOrderCommand.OrderItemCommand(i.menuId(), i.quantity()))
                .toList(),
            paymentMethod
        );
    }
    public record ItemDto(Long menuId, int quantity) {}
}
