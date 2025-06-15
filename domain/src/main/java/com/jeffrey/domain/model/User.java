package com.jeffrey.domain.model;

import com.jeffrey.domain.model.shared.BaseDate;
import com.jeffrey.domain.model.vo.Email;
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
}
