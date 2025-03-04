package io.devcommunity.developer_community.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.devcommunity.developer_community.common.JwtResponseDto;
import io.devcommunity.developer_community.domain.dto.response.UserResponseDto;
import io.devcommunity.developer_community.service.LoginService;
import io.devcommunity.developer_community.service.TokenStorageService;
import io.devcommunity.developer_community.util.JwtUtil;
import io.devcommunity.developer_community.common.UserLoginDto;
import io.devcommunity.developer_community.domain.entity.Users;
import io.devcommunity.developer_community.repository.UsersRepository;
import io.devcommunity.developer_community.util.ResponseWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j(topic = "Login And Jwt Authentication Filter")
@Component
@RequiredArgsConstructor
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UsersRepository usersRepository;
    private final JwtUtil jwtUtil;
    private final LoginService loginService;
    private final ResponseWrapper responseWrapper;
    private final TokenStorageService tokenStorageService;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            log.info("LoginAuthenticationFilter 작동");

            UserLoginDto userLoginDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);
            String findUserId = userLoginDto.getUserId();

            Optional<Users> findUsers = usersRepository.findUsersByUserName(userLoginDto.getUserId());

            if(findUsers.isEmpty()){
                throw new UsernameNotFoundException("Cannot Find Users : " + findUserId);
            }

            request.setAttribute("force", userLoginDto.isForce());

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDto.getUserId(),
                            userLoginDto.getUserPassword(),
                            null
                    )
            );
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse res, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        log.info("successfulAuthentication Method Start");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        boolean force = (boolean) request.getAttribute("force");
        String username = userDetails.getUsername();

        /*
          사용자가 이미 로그인 중 And 로그인 무시하고 새로운 로그인 요청
         */
        if(loginService.alreadyLoggedUser(username)){
            if(force){
                loginService.logoutLoggedUser(username);
            }else{
                throw new BadCredentialsException("already logged User");
            }
        }

        String userAccessToken = jwtUtil.generateAccessToken(username);
        loginService.storeUserIdAndAcToken(username, userAccessToken);

        Users users = userDetails.getUsers();
        String findRefreshToken = users.getUserRefreshToken();

        if(findRefreshToken == null || !jwtUtil.validToken(findRefreshToken)){
            tokenStorageService.storeUserRefreshToken(users);
        }

        JwtResponseDto jwtResponseDto = JwtResponseDto.builder()
                .users(UserResponseDto.of(users))
                        .accessToken(userAccessToken)
                        .refreshToken(users.getUserRefreshToken())
                        .build();

        responseWrapper.convertObjectToResponse(res, jwtResponseDto);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error("Authentication failed , {}", failed.getMessage());
        response.setStatus(403);
    }
}
