package com.jeffrey.domain.shared;

import com.jeffrey.domain.vo.CreatedAt;
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
