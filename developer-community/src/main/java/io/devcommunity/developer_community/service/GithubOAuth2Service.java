package io.devcommunity.developer_community.service;

import io.devcommunity.developer_community.repository.UsersRepository;
import io.devcommunity.developer_community.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubOAuth2Service extends DefaultOAuth2UserService {
    private final UsersRepository usersRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String githubId = oAuth2User.getAttribute("id").toString();
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");

        return super.loadUser(userRequest);
    }
}


