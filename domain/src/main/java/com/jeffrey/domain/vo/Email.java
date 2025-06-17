package com.jeffrey.domain.vo;

import lombok.NonNull;
import lombok.Value;

import java.util.regex.Pattern;

@Value
public class Email {
    @NonNull
    String value;

    static Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
        Pattern.CASE_INSENSITIVE
    );

    public Email(String value) {
        if (value == null || !EMAIL_PATTERN.matcher(value.trim()).matches()) {
            throw new IllegalArgumentException("Invalid email: " + value);
        }
        this.value = value.trim().toLowerCase();
    }
}