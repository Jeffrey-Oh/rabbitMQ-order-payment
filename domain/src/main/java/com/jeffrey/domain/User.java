package com.jeffrey.domain;

import com.jeffrey.domain.shared.BaseDate;
import com.jeffrey.domain.vo.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseDate {
    private Long userId;
    private String username;
    private Email email;
    private String passwordHash;
    private String refreshToken;

    public void loggedIn(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void logout() {
        this.refreshToken = null;
    }

}
