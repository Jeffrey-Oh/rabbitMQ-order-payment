package com.jeffrey.storage.repository;

import com.jeffrey.storage.entity.OrderEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT o FROM OrderEntity o WHERE o.orderId = :orderId")
    Optional<OrderEntity> lockFindByOrderId(Long orderId);

    Optional<OrderEntity> findByOrderId(Long orderId);

}
