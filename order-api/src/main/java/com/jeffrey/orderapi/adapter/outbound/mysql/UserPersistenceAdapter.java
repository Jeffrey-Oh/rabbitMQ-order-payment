package com.jeffrey.orderapi.adapter.outbound.mysql;

import com.jeffrey.domain.User;
import com.jeffrey.orderapi.adapter.outbound.mapper.UserMapper;
import com.jeffrey.orderapi.application.port.out.UserCommandPort;
import com.jeffrey.orderapi.entity.UserEntity;
import com.jeffrey.orderapi.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserCommandPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = userJpaRepository.save(userMapper.toEntity(user));
        return userMapper.toDomain(userEntity);
    }
}
