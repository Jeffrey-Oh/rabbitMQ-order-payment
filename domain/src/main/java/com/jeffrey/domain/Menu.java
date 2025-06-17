package com.jeffrey.domain;

import com.jeffrey.domain.shared.BaseDate;
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
    private boolean available;
}
