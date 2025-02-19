package io.devcommunity.developer_community.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final RedisTemplate<String, String> redisTemplate;
    private final UsersService usersService;

    @Value("${ACCESS_TOKEN_EXPIRATION_TIME}")
    private String acTokenExTime;
}
