package com.jeffrey.domain.entity;

import com.jeffrey.common.PaymentMethod;
import com.jeffrey.domain.entity.shared.CreatedAtColumn;
import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class PaymentEntity extends CreatedAtColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private Long paymentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false)
    private OrderEntity order;

    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    private PaymentMethod method;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "status", nullable = false)
    private String status;

}
