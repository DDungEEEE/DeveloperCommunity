package io.devcommunity.developer_community.common;

import lombok.Getter;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class GithubUserDto{
    private String id;
    private String name;

    public GithubUserDto(OAuth2User oAuth2User){
        this.id = oAuth2User.getAttribute("id").toString();
        this.name = oAuth2User.getAttribute("name");
}
}
