package com.jeffrey.domain.vo;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class UpdatedAt {
    LocalDateTime value;

    public UpdatedAt(LocalDateTime value) {
        if (value == null) {
            this.value = null;
            return;
        }
        if (value.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("UpdatedAt cannot be in the future: " + value);
        }
        this.value = value;
    }
}
