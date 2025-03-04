package io.devcommunity.developer_community.domain.dto.response;

import io.devcommunity.developer_community.domain.entity.UserRole;
import io.devcommunity.developer_community.domain.entity.Users;
import lombok.*;

import java.util.UUID;

@Builder @Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto {

    private UUID userId;

    private String userName;

    private String userEmail;

    private String userPassword;

    private String userNickName;

    private UserRole userRole;

    public static UserResponseDto of(Users user){
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .userPassword(user.getUserPassword())
                .userNickName(user.getUserName())
                .userRole(user.getUserRole())
                .build();
    }
}
