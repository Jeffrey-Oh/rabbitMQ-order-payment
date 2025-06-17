package com.jeffrey.orderapi.repository;

import com.jeffrey.orderapi.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
}
