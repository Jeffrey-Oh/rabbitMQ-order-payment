package com.jeffrey.orderapi.application.port.out;

import com.jeffrey.domain.User;

public interface UserCommandPort {

    User save(User user);

}
