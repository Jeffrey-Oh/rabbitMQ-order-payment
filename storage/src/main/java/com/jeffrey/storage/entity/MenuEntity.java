package com.jeffrey.storage.entity;

import com.jeffrey.storage.entity.shared.BaseDateColumn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "menu")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "available", nullable = false)
    private boolean available;

}
