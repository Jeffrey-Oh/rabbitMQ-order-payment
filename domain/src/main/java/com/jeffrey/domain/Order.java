package com.jeffrey.domain;

import com.jeffrey.common.enums.OrderStatus;
import com.jeffrey.domain.shared.BaseDate;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
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

    public void addItem(OrderItem item) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        orderItems.add(item);
        item.setOrder(this);
    }

    public void confirm() {
        if (this.status != OrderStatus.CREATED) {
            throw new IllegalStateException("주문 상태가 생성되지 않았습니다.");
        }
        this.status = OrderStatus.CONFIRMED;
    }

    public void completed() {
        if (this.status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("주문 상태가 확인되지 않았습니다.");
        }
        this.status = OrderStatus.COMPLETED;
    }

}
