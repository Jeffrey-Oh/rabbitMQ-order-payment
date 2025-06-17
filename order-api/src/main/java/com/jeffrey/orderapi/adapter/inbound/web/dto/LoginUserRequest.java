package com.jeffrey.orderapi.adapter.inbound.web.dto;

import com.jeffrey.orderapi.application.usecase.command.LoginUserCommand;
import com.jeffrey.orderapi.utils.AES256Util;

public record LoginUserRequest(
    String username,
    String password
) {
    public LoginUserCommand toCommand() {
        String passwordHash = AES256Util.encrypt(password);
        return new LoginUserCommand(username, passwordHash);
    }
}
