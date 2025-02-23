package io.devcommunity.developer_community.domain.dto;

import io.devcommunity.developer_community.domain.entity.Users;
import lombok.*;

import java.util.UUID;

public interface UserDto {

    @Data
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class Create{
        private String userName;

        private String userEmail;

        private String userPassword;

        private String userNickname;

        private Users.Role role;

        public Users asUser(){
            return Users.builder()
                    .userName(this.userName)
                    .userEmail(this.userEmail)
                    .userNickname(this.userNickname)
                    .role(this.role)
                    .build();
        }
    }

    @Builder
    class Result{
        private UUID userId;

        private String userName;

        private String userEmail;

        private String userPassword;

        private String userNickName;

        private Users.Role role;

        public static Result of(Users user){
            return Result.builder()
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .userEmail(user.getUserEmail())
                    .userPassword(user.getUserPassword())
                    .userNickName(user.getUserName())
                    .role(user.getRole())
                    .build();
        }
    }
}
