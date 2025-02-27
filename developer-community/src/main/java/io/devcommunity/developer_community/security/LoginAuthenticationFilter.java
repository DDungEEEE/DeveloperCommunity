package io.devcommunity.developer_community.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.devcommunity.developer_community.service.LoginService;
import io.devcommunity.developer_community.util.JwtUtil;
import io.devcommunity.developer_community.common.UserLoginDto;
import io.devcommunity.developer_community.domain.entity.Users;
import io.devcommunity.developer_community.repository.UsersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j(topic = "Login And Jwt Authentication Filter")
@RequiredArgsConstructor
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final UsersRepository usersRepository;
    private final JwtUtil jwtUtil;
    private final LoginService loginService;

    @Override
    public void setFilterProcessesUrl(String filterProcessesUrl) {
        super.setFilterProcessesUrl("/api/v1/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
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
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        boolean force = (boolean) request.getAttribute("force");
        String username = userDetails.getUsername();

        if(loginService.alreadyLoggedUser(username)){
            if(force){
                loginService.logoutLoggedUser(username);
            }else{
                throw new
            }
        }


        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(403);
    }
}
