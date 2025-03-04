package io.devcommunity.developer_community.controller;

import io.devcommunity.developer_community.common.JwtResponseDto;
import io.devcommunity.developer_community.common.UserLoginDto;
import io.devcommunity.developer_community.domain.dto.request.UserCreateReqDto;
import io.devcommunity.developer_community.domain.dto.response.UserResponseDto;
import io.devcommunity.developer_community.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UsersService usersService;

    @Operation
    @PostMapping("/login")
    public JwtResponseDto userLogin(@RequestBody UserLoginDto userLoginDto){
        return JwtResponseDto.builder().build();
    }

    @Operation(summary = "사용자 controller")
    @PostMapping
    public UserResponseDto signUp(@RequestBody UserCreateReqDto userCreateReqDto){
        return usersService.signUpUser(userCreateReqDto);
    }
}
