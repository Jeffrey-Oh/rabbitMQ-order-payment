package com.jeffrey.orderapi.repository;

import com.jeffrey.orderapi.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {
}
