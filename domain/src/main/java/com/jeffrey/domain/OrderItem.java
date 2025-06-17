package com.jeffrey.domain;

import com.jeffrey.domain.shared.CreatedDate;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends CreatedDate {
    private Long orderItemId;
    private Order order;
    private Menu menu;
    private int quantity;
    private int price;
    private int totalPrice;

    public void setOrder(Order order) {
        this.order = order;
        if (order != null && !order.getOrderItems().contains(this)) {
            order.getOrderItems().add(this);
        }
    }

}
