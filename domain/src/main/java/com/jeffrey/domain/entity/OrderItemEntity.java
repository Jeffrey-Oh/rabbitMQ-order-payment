package com.jeffrey.domain.entity;

import com.jeffrey.domain.entity.shared.CreatedAtColumn;
import jakarta.persistence.*;

@Entity
@Table(name = "orderItem")
public class OrderItemEntity extends CreatedAtColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItemId")
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false)
    private OrderEntity order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuId", nullable = false)
    private MenuEntity menu;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "totalPrice", nullable = false)
    private int totalPrice;

}
