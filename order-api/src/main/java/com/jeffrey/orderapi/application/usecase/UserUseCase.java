package com.jeffrey.orderapi.application.usecase;

import com.jeffrey.orderapi.adapter.inbound.web.dto.CreateUserRequest;
import com.jeffrey.orderapi.application.usecase.result.CreatedUserResult;

public interface UserUseCase {

    CreatedUserResult createUser(CreateUserRequest.CreateUserCommand command);

}
