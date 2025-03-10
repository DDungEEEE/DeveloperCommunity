package io.devcommunity.developer_community.controller;

import io.devcommunity.developer_community.common.OAuth2UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class OAuth2Controller {
    @GetMapping("/success")
    public ResponseEntity<OAuth2UserDto> getUserGitInfo(@AuthenticationPrincipal OAuth2User oAuth2User){
        OAuth2UserDto user = new OAuth2UserDto(oAuth2User);
        return ResponseEntity.ok(user);

    }
}
