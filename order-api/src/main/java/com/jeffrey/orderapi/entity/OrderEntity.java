package com.jeffrey.orderapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jeffrey.common.OrderStatus;
import com.jeffrey.domain.OrderItem;
import com.jeffrey.orderapi.entity.shared.BaseDateColumn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    public void addItem(OrderItemEntity item) {
        orderItems.add(item);
        item.setOrder(this);
    }

}
