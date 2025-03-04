package io.devcommunity.developer_community.service;

import io.devcommunity.developer_community.domain.dto.request.UserCreateReqDto;
import io.devcommunity.developer_community.domain.dto.response.UserResponseDto;
import io.devcommunity.developer_community.domain.entity.Users;
import io.devcommunity.developer_community.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto signUpUser(UserCreateReqDto userCreateReqDto){
       userCreateReqDto.setUserPassword(passwordEncoder.encode(userCreateReqDto.getUserPassword()));
        Users user = userCreateReqDto.asUser();

        Users saveUser = usersRepository.save(user);

        return UserResponseDto.of(saveUser);
    }
}
