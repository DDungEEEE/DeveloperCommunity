package io.devcommunity.developer_community.exception;

import io.devcommunity.developer_community.common.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private String divisionCode;
    private int httpStatus;

    @Builder
    public ErrorResponse(ErrorCode errorCode) {
       this.message = errorCode.getMessage();
       this.divisionCode = errorCode.getDivisionCode();
       this.httpStatus = errorCode.getHttpStatus();
    }
}
