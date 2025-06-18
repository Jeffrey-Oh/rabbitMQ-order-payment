package com.jeffrey.orderapi.infrastructure.resolver;

import com.jeffrey.orderapi.infrastructure.annotation.AuthUserId;
import com.jeffrey.orderapi.infrastructure.exception.UnauthorizedException;
import com.jeffrey.orderapi.infrastructure.jwt.JwtTokenProvider;
import com.jeffrey.orderapi.infrastructure.jwt.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthUserIdResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthUserId.class) &&
            (parameter.getParameterType().equals(Long.class) || parameter.getParameterType().equals(long.class));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        var authHeader = webRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || authHeader.isEmpty())
            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "Authorization header is missing");

        if (!authHeader.startsWith(JwtTokenProvider.PREFIX))
            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "Invalid authorization header format");

        var accessToken = authHeader.substring(JwtTokenProvider.PREFIX.length());
        return jwtTokenProvider.getUserId(accessToken, TokenType.ACCESS_TOKEN);
    }
}
