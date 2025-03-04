package io.devcommunity.developer_community.common;


import io.devcommunity.developer_community.domain.dto.response.UserResponseDto;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder @Getter
public class JwtResponseDto {
    private UserResponseDto users;

    private String accessToken;

    private String refreshToken;
}
