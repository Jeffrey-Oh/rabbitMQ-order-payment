package com.jeffrey.port.out;

import com.jeffrey.domain.User;

public interface UserQueryPort {

    User findByUsernameAndPasswordHash(String username, String passwordHash);

}
