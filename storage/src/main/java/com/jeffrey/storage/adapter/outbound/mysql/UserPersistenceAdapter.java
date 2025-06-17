package com.jeffrey.storage.adapter.outbound.mysql;

import com.jeffrey.domain.User;
import com.jeffrey.port.out.UserCommandPort;
import com.jeffrey.port.out.UserQueryPort;
import com.jeffrey.storage.adapter.outbound.mapper.UserMapper;
import com.jeffrey.storage.entity.UserEntity;
import com.jeffrey.storage.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserCommandPort, UserQueryPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = userJpaRepository.save(userMapper.toEntity(user));
        return userMapper.toDomain(userEntity);
    }

    @Override
    public User findByUsernameAndPasswordHash(String username, String passwordHash) {
        return userMapper.toDomain(userJpaRepository.findByUsernameAndPasswordHash(username, passwordHash).orElse(null));
    }
}
