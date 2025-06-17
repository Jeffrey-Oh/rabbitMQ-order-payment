package com.jeffrey.orderapi.infrastructure.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
    String accessSecret,
    String refreshSecret,
    long accessExpiresTime,
    long refreshExpiresTime
) {
}
