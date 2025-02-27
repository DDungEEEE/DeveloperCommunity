package io.devcommunity.developer_community.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    public enum Role{
            BACKEND, FRONTEND, PM, DESIGNER, MOBILE, ETC
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "user_email", unique = true)
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_nickname", unique = true)
    private String userNickname;

    @Column(name = "user_role")
    private Role userRole;

    @Column(name = "user_refersh_token")
    private String userRefreshToken;

}
