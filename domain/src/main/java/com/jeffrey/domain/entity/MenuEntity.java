package com.jeffrey.domain.entity;

import com.jeffrey.domain.entity.shared.BaseDateColumn;
import jakarta.persistence.*;

@Entity
@Table(name = "menu")
public class MenuEntity extends BaseDateColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuId")
    private Long menuId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "isAvailable", nullable = false)
    private boolean isAvailable;

}
