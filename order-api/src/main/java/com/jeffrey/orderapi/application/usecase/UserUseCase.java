package com.jeffrey.orderapi.application.usecase;

import com.jeffrey.orderapi.adapter.inbound.web.dto.CreateUserRequest;
import com.jeffrey.orderapi.application.usecase.command.LoginUserCommand;
import com.jeffrey.orderapi.application.usecase.result.LoggedInUserResult;

public interface UserUseCase {

    void createUser(CreateUserRequest.CreateUserCommand command);

    LoggedInUserResult login(LoginUserCommand command);

}
