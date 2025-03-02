package io.devcommunity.developer_community.service;

import io.devcommunity.developer_community.domain.dto.UserDto;
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

    public UserDto.Result signUpUser(UserDto.Create create){
        create.setUserPassword(passwordEncoder.encode(create.getUserPassword()));
        Users user = create.asUser();

        Users saveUser = usersRepository.save(user);
        return UserDto.Result.of(saveUser);
    }
}
