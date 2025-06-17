package com.jeffrey.orderapi.repository;

import com.jeffrey.orderapi.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long> {
}
