package com.jeffrey.orderapi.adapter.inbound.web;

import com.jeffrey.common.enums.OrderStatus;
import com.jeffrey.orderapi.adapter.inbound.web.dto.CreateOrderRequest;
import com.jeffrey.orderapi.adapter.inbound.web.dto.CreateOrderResponse;
import com.jeffrey.orderapi.application.usecase.OrderUseCase;
import com.jeffrey.orderapi.infrastructure.annotation.AuthUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/polling/{orderId}")
    public ResponseEntity<OrderStatus> pollingOrderStatus(
        @AuthUserId Long userId,
        @PathVariable Long orderId
    ) {
        OrderStatus status = orderUseCase.pollingOrderStatus(userId, orderId);
        return ResponseEntity.ok(status);
    }

}

