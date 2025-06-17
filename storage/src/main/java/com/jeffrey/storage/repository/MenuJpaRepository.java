package com.jeffrey.storage.repository;

import com.jeffrey.storage.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuJpaRepository extends JpaRepository<MenuEntity, Long> {

    Optional<MenuEntity> findByMenuId(Long menuId);

}
