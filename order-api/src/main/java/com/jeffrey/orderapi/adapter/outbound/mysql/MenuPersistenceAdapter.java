package com.jeffrey.orderapi.adapter.outbound.mysql;

import com.jeffrey.orderapi.application.port.out.MenuCommandPort;
import com.jeffrey.orderapi.entity.MenuEntity;
import com.jeffrey.domain.Menu;
import com.jeffrey.orderapi.repository.MenuJpaRepository;
import com.jeffrey.orderapi.adapter.outbound.mapper.MenuMapper;
import com.jeffrey.orderapi.application.port.out.MenuQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuPersistenceAdapter implements MenuQueryPort, MenuCommandPort {

    private final MenuJpaRepository menuJpaRepository;
    private final MenuMapper menuMapper;

    @Override
    public Menu findByMenuId(Long menuId) {
        MenuEntity menuEntity = menuJpaRepository.findByMenuId(menuId).orElse(null);
        return menuMapper.toDomain(menuEntity);
    }

    @Override
    public Menu save(Menu menu) {
        MenuEntity menuEntity = menuMapper.toEntity(menu);
        MenuEntity save = menuJpaRepository.save(menuEntity);
        return menuMapper.toDomain(save);
    }
}
