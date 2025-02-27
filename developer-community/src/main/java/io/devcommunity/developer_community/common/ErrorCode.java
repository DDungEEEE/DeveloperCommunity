package io.devcommunity.developer_community.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    ALREADY_USER_LOGGED_ERROR("AUTH_001", 401, "이미 로그인 중인 사용자입니다.");

    ErrorCode(String divisionCode, int httpStatus, String message) {
        this.divisionCode = divisionCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    private String divisionCode;
    private int httpStatus;
    private String message;
}
