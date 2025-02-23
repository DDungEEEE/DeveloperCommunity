package io.devcommunity.controller;

import io.devcommunity.developer_community.domain.dto.UserDto;
import io.devcommunity.developer_community.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/test/user")
public class UserController {
    private final UsersService usersService;

    @PostMapping
    public UserDto.Result signUp(@RequestBody UserDto.Create userCreate){
        return usersService.signUpUser(userCreate);
    }
}
