package io.devcommunity.developer_community.service;

import io.devcommunity.developer_community.repository.UsersRepository;
import io.devcommunity.developer_community.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

//@Service
//public class GithubOAuth2Service extends DefaultOAuth2UserService {
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        System.out.println("userRequest : " + userRequest);
//        System.out.println("getClientRegistration: " + userRequest.getClientRegistration());
//        System.out.println("getAccessToken: " + userRequest.getAccessToken());
//        System.out.println("getAttributes: " +super.loadUser(userRequest).getAttributes());
//        return super.loadUser(userRequest);
//    }
//}

@Service
@RequiredArgsConstructor
public class GithubOAuth2Service {

    private final JwtUtil jwtUtil;
    private final UsersRepository usersRepository;

    private String ACCESS_TOKEN_REQUEST_URL;

}

