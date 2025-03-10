package io.devcommunity.developer_community.common;

import lombok.Getter;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class OAuth2UserDto{
    private String id;
    private String name;
    private String email;

    public OAuth2UserDto(OAuth2User oAuth2User){
        this.id = oAuth2User.getAttribute("id").toString();
        this.name = oAuth2User.getAttribute("name");
        this.email = oAuth2User.getAttribute("email");
    }
}
