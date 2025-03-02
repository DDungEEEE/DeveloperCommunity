package io.devcommunity.developer_community.security;

import io.devcommunity.developer_community.domain.entity.Users;
import io.devcommunity.developer_community.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Object cachedUser = redisTemplate.opsForValue().get(username);
//
//        if(cachedUser instanceof Users users){
//            log.info("Cache Hit Users");
//            return new UserDetailsImpl(users);
//        }

        log.error("usrename : {}", username);
        Optional<Users> findUsers = usersRepository.findUsersByUserName(username);

        if(findUsers.isEmpty()){
            throw new UsernameNotFoundException(username + " is not found");
        }

        return new UserDetailsImpl(findUsers.get());
    }
}
