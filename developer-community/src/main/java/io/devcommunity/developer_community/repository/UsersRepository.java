package io.devcommunity.developer_community.repository;

import io.devcommunity.developer_community.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findUsersByUserName(String userName);
}
