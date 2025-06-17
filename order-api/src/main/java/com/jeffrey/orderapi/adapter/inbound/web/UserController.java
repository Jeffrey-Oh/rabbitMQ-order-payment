package com.jeffrey.orderapi.adapter.inbound.web;

import com.jeffrey.orderapi.adapter.inbound.web.dto.CreateUserRequest;
import com.jeffrey.orderapi.application.usecase.UserUseCase;
import com.jeffrey.orderapi.application.usecase.result.CreatedUserResult;
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
    public CreatedUserResult create(
        @RequestBody CreateUserRequest request
    ) {
        CreateUserRequest.CreateUserCommand command = request.toCommand();
        return userUseCase.createUser(command);
    }

}
