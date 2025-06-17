package com.jeffrey.domain;

import com.jeffrey.common.OrderStatus;
import com.jeffrey.domain.shared.BaseDate;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseDate {
    private Long orderId;
    private User user;
    private int totalAmount;
    private OrderStatus status;
    private List<OrderItem> orderItems;

    public static Order create(User user, List<OrderItem> orderItems) {
        int totalAmount = orderItems.stream()
            .mapToInt(OrderItem::getTotalPrice)
            .sum();

        Order order = Order.builder()
            .user(user)
            .orderItems(orderItems)
            .totalAmount(totalAmount)
            .status(OrderStatus.CREATED)
            .build();

        orderItems.forEach(item -> item.setOrder(order));

        return order;
    }
}
