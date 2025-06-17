package com.jeffrey.storage.adapter.outbound.mysql;

import com.jeffrey.port.out.MenuCommandPort;
import com.jeffrey.port.out.MenuQueryPort;
import com.jeffrey.storage.entity.MenuEntity;
import com.jeffrey.domain.Menu;
import com.jeffrey.storage.repository.MenuJpaRepository;
import com.jeffrey.storage.adapter.outbound.mapper.MenuMapper;
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
