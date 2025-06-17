package com.jeffrey.orderapi.application.service;

import com.jeffrey.domain.Menu;
import com.jeffrey.orderapi.application.port.out.MenuCommandPort;
import com.jeffrey.orderapi.application.usecase.MenuUseCase;
import com.jeffrey.orderapi.application.usecase.command.CreateMenuCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService implements MenuUseCase {

    private final MenuCommandPort menuCommandPort;

    @Override
    public Long create(CreateMenuCommand command) {
        return menuCommandPort.save(
            Menu.builder()
                .name(command.name())
                .description(command.description())
                .price(command.price())
                .build()
        ).getMenuId();
    }

}
