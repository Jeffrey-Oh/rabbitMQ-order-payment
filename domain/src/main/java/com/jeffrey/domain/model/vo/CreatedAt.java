package com.jeffrey.domain.model.vo;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Objects;

@Value
public class CreatedAt {
    @NonNull
    LocalDateTime value;

    // 생성 시점이 null이 아니고, 과거 또는 현재임을 validation
    public CreatedAt(LocalDateTime value) {
        Objects.requireNonNull(value, "CreatedAt must not be null");
        if (value.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("CreatedAt cannot be in the future: " + value);
        }
        this.value = value;
    }
}
