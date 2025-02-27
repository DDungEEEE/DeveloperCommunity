package io.devcommunity.developer_community.exception;

import io.devcommunity.developer_community.common.ErrorCode;
import lombok.Getter;

@Getter
public class AuthenticationException extends SecurityException{
    private String message;
    private ErrorCode errorCode;

    public AuthenticationException(String message) {
        this.message = message;
    }

    public AuthenticationException(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }
}
