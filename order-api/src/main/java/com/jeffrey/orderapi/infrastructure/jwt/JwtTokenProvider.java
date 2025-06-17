package com.jeffrey.orderapi.infrastructure.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jeffrey.orderapi.infrastructure.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final Algorithm accessAlgorithm;
    private final Algorithm refreshAlgorithm;

    public static final String PREFIX = "Bearer ";

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.accessAlgorithm = Algorithm.HMAC256(jwtProperties.accessSecret());
        this.refreshAlgorithm = Algorithm.HMAC256(jwtProperties.refreshSecret());
    }

    public Boolean validateToken(String token, TokenType type) {
        try {
            getDecodedJWT(token, type);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public DecodedJWT getDecodedJWT(String token, TokenType type) {
        var algorithm = switch (type) {
            case ACCESS_TOKEN -> accessAlgorithm;
            case REFRESH_TOKEN -> refreshAlgorithm;
        };
        var verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    public String createAccessToken(Long userId, String username, List<String> roles) {
        var now = new Date();
        var expiry = new Date(now.getTime() + jwtProperties.accessExpiresTime() * 1000);
        return JWT.create()
            .withSubject(username)
            .withIssuedAt(now)
            .withExpiresAt(expiry)
            .withClaim("roles", roles)
            .withClaim("userId", userId)
            .sign(accessAlgorithm);
    }

    public String createRefreshToken(String username, List<String> roles) {
        var now = new Date();
        var expiry = new Date(now.getTime() + jwtProperties.refreshExpiresTime() * 1000);
        return JWT.create()
            .withSubject(username)
            .withIssuedAt(now)
            .withExpiresAt(expiry)
            .withClaim("roles", roles)
            .sign(refreshAlgorithm);
    }

    public Long getTokenExpiration(String token, TokenType type) {
        var jwt = getDecodedJWT(token, type);
        return jwt.getExpiresAt().getTime();
    }

    public Long getUserId(String token, TokenType type) {
        var jwt = getDecodedJWT(token, type);
        try {
            return jwt.getClaim("userId").asLong();
        } catch (Exception e) {
            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "Missing userId in token");
        }
    }

}
