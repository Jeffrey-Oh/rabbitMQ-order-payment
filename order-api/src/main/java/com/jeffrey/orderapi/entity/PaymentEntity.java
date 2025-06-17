package com.jeffrey.orderapi.entity;

import com.jeffrey.common.PaymentMethod;
import com.jeffrey.common.PaymentStatus;
import com.jeffrey.orderapi.entity.shared.CreatedAtColumn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "payment")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

}
