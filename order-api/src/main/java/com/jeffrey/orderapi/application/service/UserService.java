package com.jeffrey.orderapi.application.service;

import com.jeffrey.domain.User;
import com.jeffrey.domain.vo.Email;
import com.jeffrey.orderapi.adapter.inbound.web.dto.CreateUserRequest;
import com.jeffrey.orderapi.application.usecase.UserUseCase;
import com.jeffrey.orderapi.application.usecase.command.LoginUserCommand;
import com.jeffrey.orderapi.application.usecase.result.LoggedInUserResult;
import com.jeffrey.orderapi.infrastructure.jwt.JwtTokenProvider;
import com.jeffrey.orderapi.infrastructure.jwt.TokenType;
import com.jeffrey.port.out.UserCommandPort;
import com.jeffrey.port.out.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserCommandPort userCommandPort;
    private final UserQueryPort userQueryPort;

    @Override
    public void createUser(CreateUserRequest.CreateUserCommand command) {
        String refreshToken = jwtTokenProvider.createRefreshToken(command.username(), List.of("ROLE_USER"));
        userCommandPort.save(
            User.builder()
                .username(command.username())
                .email(new Email(command.email()))
                .passwordHash(command.passwordHash())
                .refreshToken(refreshToken)
                .build()
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LoggedInUserResult login(LoginUserCommand command) {
        User user = userQueryPort.findByUsernameAndPasswordHash(command.username(), command.passwordHash());
        if (user == null) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getUserId(), user.getUsername(), List.of("ROLE_USER"));
        String refreshToken = jwtTokenProvider.createRefreshToken(command.username(), List.of("ROLE_USER"));

        user.loggedIn(refreshToken);
        userCommandPort.save(user);

        Long accessTokenExpiresIn = jwtTokenProvider.getTokenExpiration(accessToken, TokenType.ACCESS_TOKEN);
        Long refreshTokenExpiresIn = jwtTokenProvider.getTokenExpiration(refreshToken, TokenType.REFRESH_TOKEN);

        return new LoggedInUserResult(
            user.getUserId(),
            accessToken,
            refreshToken,
            accessTokenExpiresIn,
            refreshTokenExpiresIn
        );
    }

}
