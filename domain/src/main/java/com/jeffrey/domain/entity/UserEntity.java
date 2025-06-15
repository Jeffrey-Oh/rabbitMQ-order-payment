package com.jeffrey.domain.entity;

import com.jeffrey.domain.entity.shared.BaseDateColumn;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity extends BaseDateColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;

}
