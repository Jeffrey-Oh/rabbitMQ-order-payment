package com.jeffrey.storage.repository;

import com.jeffrey.storage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserId(Long userId);

    Optional<UserEntity> findByUsernameAndPasswordHash(String username, String passwordHash);

}
