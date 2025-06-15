package com.jeffrey.domain.model.shared;

import com.jeffrey.domain.model.vo.CreatedAt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatedDate {
    private CreatedAt createdAt;
}
