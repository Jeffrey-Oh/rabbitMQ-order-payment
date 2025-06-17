package com.jeffrey.orderapi.application.usecase.command;

public record CreateMenuCommand(String name, String description, int price) {

    public CreateMenuCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be a positive number");
        }
    }
}
