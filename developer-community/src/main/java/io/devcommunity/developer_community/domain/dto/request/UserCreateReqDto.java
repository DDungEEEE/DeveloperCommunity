package io.devcommunity.developer_community.domain.dto.request;

import io.devcommunity.developer_community.domain.entity.UserRole;
import io.devcommunity.developer_community.domain.entity.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter @Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateReqDto {

    @NotNull @NotBlank
    private String userName;

    @NotNull @NotBlank
    private String userEmail;

    private String userPassword;

    private String userNickname;

    private UserRole userRole;

    public Users asUser(){
        return Users.builder()
                .userName(this.userName)
                .userEmail(this.userEmail)
                .userNickname(this.userNickname)
                .userPassword(this.userPassword)
                .userRole(this.userRole)
                .build();
    }
}
