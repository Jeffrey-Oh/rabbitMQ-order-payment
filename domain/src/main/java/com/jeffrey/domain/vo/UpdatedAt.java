package com.jeffrey.domain.vo;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Objects;

@Value
public class UpdatedAt {
    @NonNull
    LocalDateTime value;

    public UpdatedAt(LocalDateTime value) {
        Objects.requireNonNull(value, "UpdatedAt must not be null");
        if (value.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("UpdatedAt cannot be in the future: " + value);
        }
        this.value = value;
    }
}
