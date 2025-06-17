package com.jeffrey.orderapi.application.usecase;

import com.jeffrey.orderapi.application.usecase.command.CreateMenuCommand;

public interface MenuUseCase {

    Long create(CreateMenuCommand command);

}
