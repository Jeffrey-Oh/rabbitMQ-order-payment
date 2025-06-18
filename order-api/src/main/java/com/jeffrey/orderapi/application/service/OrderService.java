package com.jeffrey.orderapi.application.service;

import com.jeffrey.common.enums.OrderStatus;
import com.jeffrey.domain.Order;
import com.jeffrey.domain.OrderItem;
import com.jeffrey.domain.User;
import com.jeffrey.orderapi.application.eventhandler.OrderCreatedEvent;
import com.jeffrey.orderapi.application.usecase.OrderUseCase;
import com.jeffrey.orderapi.application.usecase.command.CreateOrderCommand;
import com.jeffrey.orderapi.utils.AuthUserProvider;
import com.jeffrey.port.out.MenuQueryPort;
import com.jeffrey.port.out.OrderCommandPort;
import com.jeffrey.port.out.OrderQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {

    private final OrderCommandPort orderCommandPort;
    private final MenuQueryPort menuQueryPort;
    private final ApplicationEventPublisher eventPublisher;
    private final OrderQueryPort orderQueryPort;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(CreateOrderCommand command) {
        User user = AuthUserProvider.currentUser();

        List<OrderItem> orderItems = command.items().stream()
            .map(item -> {
                var menu = menuQueryPort.findByMenuId(item.menuId());
                if (menu == null) {
                    throw new IllegalArgumentException("Menu not found: " + item.menuId());
                }
                return OrderItem.builder()
                    .menu(menu)
                    .quantity(item.quantity())
                    .price(menu.getPrice())
                    .totalPrice(menu.getPrice() * item.quantity())
                    .build();
            })
            .collect(Collectors.toList());

        Order order = Order.create(user, orderItems);
        Order save = orderCommandPort.save(order);
        save.setOrderItems(orderItems);

        eventPublisher.publishEvent(new OrderCreatedEvent(save, command.paymentMethod()));

        return save.getOrderId();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderStatus pollingOrderStatus(Long userId, Long orderId) {
        Order order = orderQueryPort.findByOrderId(orderId);
        if (order != null && order.getUser().getUserId().equals(userId)) {
            return order.getStatus();
        }
        throw new IllegalArgumentException("Order not found or user does not have permission to access this order.");
    }
}
