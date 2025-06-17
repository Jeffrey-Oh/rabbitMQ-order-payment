package com.jeffrey.domain.vo;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CreatedAt {
    LocalDateTime value;

    // 생성 시점이 null이 아니고, 과거 또는 현재임을 validation
    public CreatedAt(LocalDateTime value) {
        if (value == null) {
            this.value = null;
            return;
        }
        if (value.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("CreatedAt cannot be in the future: " + value);
        }
        this.value = value;
    }
}
