package io.devcommunity.developer_community.controller;

import io.devcommunity.developer_community.common.JwtResponseDto;
import io.devcommunity.developer_community.common.OAuth2UserDto;
import io.devcommunity.developer_community.common.UserLoginDto;
import io.devcommunity.developer_community.domain.dto.request.UserCreateReqDto;
import io.devcommunity.developer_community.domain.dto.response.UserResponseDto;
import io.devcommunity.developer_community.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "사용자 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UsersService usersService;

    @Value("${CLIENT_ID}")
    private String githubClientId;

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

    @GetMapping("/githubLogin")
    public ResponseEntity<Void> githubAuth2Login(HttpServletResponse res) throws IOException {
        res.sendRedirect("https://github.com/login?clientid=" );
        return ResponseEntity.status(HttpStatus.FOUND).build();

    }
}
