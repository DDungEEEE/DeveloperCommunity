package io.devcommunity.developer_community.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    public enum Role{
            BACKEND, FRONTEND, PM, DESIGNER, MOBILE, ETC

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_enamil")
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_role")
    private Role role;

    @Column(name = "user_refersh_token")
    private String userRefreshToken;

}
