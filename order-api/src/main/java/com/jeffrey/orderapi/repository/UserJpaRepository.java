package com.jeffrey.orderapi.repository;

import com.jeffrey.orderapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserId(Long userId);

}
