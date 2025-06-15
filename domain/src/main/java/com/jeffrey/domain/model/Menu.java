package com.jeffrey.domain.model;

import com.jeffrey.domain.model.shared.BaseDate;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseDate {
    private Long menuId;
    private String name;
    private String description;
    private int price;
    private boolean isAvailable;
}
