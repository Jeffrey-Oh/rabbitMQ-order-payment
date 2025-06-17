package com.jeffrey.orderapi.adapter.inbound.web.dto;

import com.jeffrey.common.OrderStatus;

public record CreateOrderResponse(Long orderId, OrderStatus status) {
}
