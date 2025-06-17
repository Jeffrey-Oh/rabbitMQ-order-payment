package com.jeffrey.orderapi.application.service;

import com.jeffrey.domain.User;
import com.jeffrey.domain.vo.Email;
import com.jeffrey.orderapi.adapter.inbound.web.dto.CreateUserRequest;
import com.jeffrey.orderapi.application.port.out.UserCommandPort;
import com.jeffrey.orderapi.application.usecase.UserUseCase;
import com.jeffrey.orderapi.application.usecase.result.CreatedUserResult;
import com.jeffrey.orderapi.infrastructure.jwt.JwtTokenProvider;
import com.jeffrey.orderapi.infrastructure.jwt.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserCommandPort userCommandPort;

    @Override
    public CreatedUserResult createUser(CreateUserRequest.CreateUserCommand command) {
        String refreshToken = jwtTokenProvider.createRefreshToken(command.username(), List.of("ROLE_USER"));

        User saveUser = userCommandPort.save(
            User.builder()
                .username(command.username())
                .email(new Email(command.email()))
                .passwordHash(command.passwordHash())
                .refreshToken(refreshToken)
                .build()
        );

        String accessToken = jwtTokenProvider.createAccessToken(saveUser.getUserId(), saveUser.getUsername(), List.of("ROLE_USER"));

        Long accessTokenExpiresIn = jwtTokenProvider.getTokenExpiration(accessToken, TokenType.ACCESS_TOKEN);
        Long refreshTokenExpiresIn = jwtTokenProvider.getTokenExpiration(refreshToken, TokenType.REFRESH_TOKEN);

        return new CreatedUserResult(
            saveUser.getUserId(),
            accessToken,
            refreshToken,
            accessTokenExpiresIn,
            refreshTokenExpiresIn
        );
    }

}
