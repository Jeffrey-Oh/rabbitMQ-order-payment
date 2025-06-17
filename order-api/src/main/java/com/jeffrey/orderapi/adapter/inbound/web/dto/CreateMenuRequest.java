package com.jeffrey.orderapi.adapter.inbound.web.dto;

import com.jeffrey.orderapi.application.usecase.command.CreateMenuCommand;

public record CreateMenuRequest(
    String name,
    String description,
    int price
) {
    public CreateMenuCommand toCommand() {
        return new CreateMenuCommand(name, description, price);
    }
}
