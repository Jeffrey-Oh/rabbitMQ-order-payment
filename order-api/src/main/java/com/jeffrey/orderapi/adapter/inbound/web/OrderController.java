package com.jeffrey.orderapi.adapter.inbound.web;

import com.jeffrey.common.enums.OrderStatus;
import com.jeffrey.orderapi.adapter.inbound.web.dto.CreateOrderRequest;
import com.jeffrey.orderapi.adapter.inbound.web.dto.CreateOrderResponse;
import com.jeffrey.orderapi.application.usecase.OrderUseCase;
import com.jeffrey.orderapi.infrastructure.annotation.AuthUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderUseCase orderUseCase;

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(
        @AuthUserId Long userId,
        @RequestBody CreateOrderRequest request
    ) {
        Long orderId = orderUseCase.createOrder(request.toCommand(userId));

        CreateOrderResponse resp = new CreateOrderResponse(orderId, OrderStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}

