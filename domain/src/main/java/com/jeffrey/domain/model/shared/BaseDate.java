package com.jeffrey.domain.model.shared;

import com.jeffrey.domain.model.vo.CreatedAt;
import com.jeffrey.domain.model.vo.UpdatedAt;
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
public class BaseDate {
    private CreatedAt createdAt;
    private UpdatedAt updatedAt;
}
