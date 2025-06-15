package com.jeffrey.domain.repository;

import com.jeffrey.domain.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuJpaRepository extends JpaRepository<MenuEntity, Long> {
}
