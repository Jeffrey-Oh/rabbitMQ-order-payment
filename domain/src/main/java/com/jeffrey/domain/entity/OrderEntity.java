package com.jeffrey.domain.entity;

import com.jeffrey.common.OrderStatus;
import com.jeffrey.domain.entity.shared.BaseDateColumn;
import jakarta.persistence.*;

@Entity
@Table(name = "order")
public class OrderEntity extends BaseDateColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @Column(name = "totalAmount", nullable = false)
    private int totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

}
