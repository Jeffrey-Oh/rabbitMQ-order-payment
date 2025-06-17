package com.jeffrey.orderapi.application.usecase.command;

public record LoginUserCommand(
    String username,
    String passwordHash
) {
}
