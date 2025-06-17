package com.jeffrey.orderapi.adapter.outbound.mysql;

import com.jeffrey.domain.Order;
import com.jeffrey.orderapi.entity.OrderEntity;
import com.jeffrey.orderapi.repository.OrderJpaRepository;
import com.jeffrey.orderapi.adapter.outbound.mapper.OrderMapper;
import com.jeffrey.orderapi.application.port.out.OrderCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderCommandPort {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order save(Order order) {
        OrderEntity entity = orderMapper.toEntity(order);
        OrderEntity save = orderJpaRepository.save(entity);
        return orderMapper.toDomain(save);
    }

}
