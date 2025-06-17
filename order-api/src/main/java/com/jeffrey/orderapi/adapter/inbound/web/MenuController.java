package com.jeffrey.orderapi.adapter.inbound.web;

import com.jeffrey.orderapi.adapter.inbound.web.dto.CreateMenuRequest;
import com.jeffrey.orderapi.application.usecase.MenuUseCase;
import com.jeffrey.orderapi.application.usecase.command.CreateMenuCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuUseCase menuUseCase;

    @PostMapping
    public Long create(
        @RequestBody CreateMenuRequest request
    ) {
        CreateMenuCommand command = request.toCommand();
        return menuUseCase.create(command);
    }

}
