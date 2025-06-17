package com.jeffrey.orderapi.adapter.inbound.web;

import com.jeffrey.orderapi.adapter.inbound.web.dto.CreateUserRequest;
import com.jeffrey.orderapi.adapter.inbound.web.dto.LoginUserRequest;
import com.jeffrey.orderapi.application.usecase.UserUseCase;
import com.jeffrey.orderapi.application.usecase.command.LoginUserCommand;
import com.jeffrey.orderapi.application.usecase.result.LoggedInUserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping
    public void create(
        @RequestBody CreateUserRequest request
    ) {
        CreateUserRequest.CreateUserCommand command = request.toCommand();
        userUseCase.createUser(command);
    }

    @PostMapping("/login")
    public LoggedInUserResult login(
        @RequestBody LoginUserRequest request
    ) {
        LoginUserCommand command = request.toCommand();
        return userUseCase.login(command);
    }

}
