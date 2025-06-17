package com.jeffrey.orderapi.adapter.inbound.web.dto;

import com.jeffrey.orderapi.utils.AES256Util;

public record CreateUserRequest(
    String username,
    String email,
    String password
) {
    public CreateUserCommand toCommand() {
        String passwordHash = AES256Util.encrypt(password);
        return new CreateUserCommand(username, email, passwordHash);
    }

    public record CreateUserCommand(
        String username,
        String email,
        String passwordHash
    ) {}
}