package com.jeffrey.orderapi.application.usecase.result;

public record CreatedUserResult(
    Long userId,
    String accessToken,
    String refreshToken,
    Long accessTokenExpiresIn,
    Long refreshTokenExpiresIn
) {
}
