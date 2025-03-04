package io.devcommunity.developer_community.config;

import io.devcommunity.developer_community.repository.UsersRepository;
import io.devcommunity.developer_community.security.LoginAuthenticationFilter;
import io.devcommunity.developer_community.service.LoginService;
import io.devcommunity.developer_community.service.TokenStorageService;
import io.devcommunity.developer_community.util.JwtUtil;
import io.devcommunity.developer_community.util.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.ForwardedHeaderFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UsersRepository usersRepository;
    private final JwtUtil jwtUtil;
    private final LoginService loginService;
    private final ResponseWrapper responseWrapper;
    private final TokenStorageService tokenStorageService;

    @Bean
    public ForwardedHeaderFilter filter(){
        return new ForwardedHeaderFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public LoginAuthenticationFilter loginAuthenticationFilter(AuthenticationManager authenticationManager) {
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter(usersRepository, jwtUtil, loginService, responseWrapper, tokenStorageService);
        loginAuthenticationFilter.setAuthenticationManager(authenticationManager);
        loginAuthenticationFilter.setFilterProcessesUrl("/api/v1/user/login");

        return loginAuthenticationFilter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, LoginAuthenticationFilter loginAuthenticationFilter) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers("/swagger", "/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers( "/").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/api/v1/user").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/api/v1/user/login").permitAll()
//                                .requestMatchers("/api/v1/**").hasAnyRole("AUTH")
                                .requestMatchers("/**").permitAll()
                                .anyRequest().authenticated());

        httpSecurity.addFilterBefore(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
