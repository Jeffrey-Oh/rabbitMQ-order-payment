package com.jeffrey.storage.adapter.outbound.mysql;

import com.jeffrey.domain.Order;
import com.jeffrey.port.out.OrderCommandPort;
import com.jeffrey.port.out.OrderQueryPort;
import com.jeffrey.storage.adapter.outbound.mapper.OrderMapper;
import com.jeffrey.storage.entity.OrderEntity;
import com.jeffrey.storage.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderCommandPort, OrderQueryPort {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order save(Order order) {
        OrderEntity entity = orderMapper.toEntity(order);
        OrderEntity save = orderJpaRepository.save(entity);
        return orderMapper.toDomain(save);
    }

    @Override
    public Order findByOrderId(Long orderId) {
        return orderMapper.toDomain(orderJpaRepository.findByOrderId(orderId).orElse(null));
    }

}
