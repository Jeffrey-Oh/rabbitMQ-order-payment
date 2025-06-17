package com.jeffrey.orderapi.infrastructure.jwt;

import com.jeffrey.domain.vo.CreatedAt;
import com.jeffrey.domain.vo.UpdatedAt;
import com.jeffrey.storage.entity.UserEntity;
import com.jeffrey.domain.User;
import com.jeffrey.domain.vo.Email;
import com.jeffrey.storage.repository.UserJpaRepository;
import com.jeffrey.orderapi.infrastructure.config.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserJpaRepository userJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final StringRedisTemplate redisTemplate;

    private static final String PREFIX_LOGOUT_REFRESH_TOKEN = "user:refreshToken:";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        TokenType tokenType;
        if (request.getRequestURI().contains("/token/refresh")) {
            if (redisTemplate.hasKey(PREFIX_LOGOUT_REFRESH_TOKEN + token)) {
                throw new InsufficientAuthenticationException("Invalid refresh token");
            }
            tokenType = TokenType.REFRESH_TOKEN;
        } else {
            tokenType = TokenType.ACCESS_TOKEN;
        }

        if (!jwtTokenProvider.validateToken(token, tokenType)) {
            throw new InsufficientAuthenticationException("Invalid token");
        }

        Long userId = jwtTokenProvider.getUserId(token, tokenType);
        UserEntity userEntity = userJpaRepository.findByUserId(userId).orElse(null);
        if (userEntity == null) {
            throw new InsufficientAuthenticationException("User not found");
        }
        User user = User.builder()
            .userId(userEntity.getUserId())
            .username(userEntity.getUsername())
            .email(new Email(userEntity.getEmail()))
            .createdAt(new CreatedAt(userEntity.getCreatedAt()))
            .updatedAt(new UpdatedAt(userEntity.getUpdatedAt()))
            .build();
        CustomUserDetails userDetails = new CustomUserDetails(user);

        var authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities()
        );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        var bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!(bearer == null || bearer.isEmpty()) && bearer.startsWith(JwtTokenProvider.PREFIX)) {
            return bearer.substring(7);
        } else return null;
    }
}
