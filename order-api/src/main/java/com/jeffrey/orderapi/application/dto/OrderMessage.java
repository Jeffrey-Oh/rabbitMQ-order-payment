package com.jeffrey.orderapi.application.dto;

import com.jeffrey.common.PaymentMethod;
import com.jeffrey.domain.Order;
import com.jeffrey.domain.OrderItem;
import com.jeffrey.domain.vo.CreatedAt;

import java.util.List;

public record OrderMessage(
    Long orderId,
    Long userId,
    PaymentMethod paymentMethod,
    List<OrderItemMessage> items,
    int totalAmount,
    CreatedAt createdAt
) {
    public static OrderMessage from(Order order, PaymentMethod paymentMethod) {
        return new OrderMessage(
            order.getOrderId(),
            order.getUser().getUserId(),
            paymentMethod,
            order.getOrderItems().stream().map(OrderItemMessage::from).toList(),
            order.getTotalAmount(),
            order.getCreatedAt()
        );
    }

    public record OrderItemMessage(
        Long menuId,
        int quantity,
        int price,
        int totalPrice
    ) {
        public static OrderItemMessage from(OrderItem item) {
            return new OrderItemMessage(
                item.getMenu().getMenuId(),
                item.getQuantity(),
                item.getPrice(),
                item.getTotalPrice()
            );
        }
    }
}