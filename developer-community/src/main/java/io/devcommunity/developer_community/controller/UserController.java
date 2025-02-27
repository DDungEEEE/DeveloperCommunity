package io.devcommunity.developer_community.controller;

import io.devcommunity.developer_community.domain.dto.UserDto;
import io.devcommunity.developer_community.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test/user")
public class UserController {
    private final UsersService usersService;

    @Operation(summary = "사용자 controller")
    @PostMapping
    public UserDto.Result signUp(@RequestBody UserDto.Create userCreate){
        return usersService.signUpUser(userCreate);
    }
}
