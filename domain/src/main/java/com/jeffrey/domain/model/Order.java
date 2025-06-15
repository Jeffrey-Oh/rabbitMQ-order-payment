package com.jeffrey.domain.model;

import com.jeffrey.common.OrderStatus;
import com.jeffrey.domain.model.shared.BaseDate;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
}
