package io.devcommunity.developer_community.service;

import io.devcommunity.developer_community.domain.entity.Users;
import io.devcommunity.developer_community.repository.UsersRepository;
import io.devcommunity.developer_community.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenStorageService {
    private final UsersRepository usersRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void storeUserRefreshToken(Users users){
        String generatedRefreshToken = jwtUtil.generateRefreshToken(users.getUserName());
        users.setUserRefreshToken(generatedRefreshToken);
        usersRepository.save(users);
    }
}
