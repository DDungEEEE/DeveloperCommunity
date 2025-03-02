package io.devcommunity.developer_community.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${ACCESS_TOKEN_EXPIRATION_TIME}")
    private int acTokenExTime;

    // 이미 로그인 중인 사용자가 있는지 확인
    public boolean alreadyLoggedUser(String userId){
        String findUser = redisTemplate.opsForValue().get(userId);
        return findUser != null;
    }

    // 유저 로그인 시 Redis 저장소에 userId, AccessToken 저장
    public void storeUserIdAndAcToken(String userId, String accessToken){
        long redisAcTokenTime = acTokenExTime;
        redisTemplate.opsForValue().set(userId, accessToken, redisAcTokenTime, TimeUnit.MILLISECONDS);
    }

    public void logoutLoggedUser(String userId){
        redisTemplate.delete(userId);
    }
}
