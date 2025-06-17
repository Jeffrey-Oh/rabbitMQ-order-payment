package com.jeffrey.orderapi.utils;

import com.jeffrey.domain.User;
import com.jeffrey.orderapi.infrastructure.config.CustomUserDetails;
import com.jeffrey.orderapi.infrastructure.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUserProvider {
    public static User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof CustomUserDetails cud)) {
            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "인증 정보가 없습니다.");
        }
        return cud.getUser();
    }
}
