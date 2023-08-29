package com.hotstarting.todocode.global.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    private final int statusCode;
    private final String statusName;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.statusCode = errorCode.getStatus().value();    // 401
        this.statusName = errorCode.getStatus().name();     // UNAUTHORIZED
        //                errorCode.name()                  // NOT_EXPIRED_TOKEN_YET
        this.message = errorCode.getMessage();              // "토큰이 아직 만료되지 않았습니다."
    }
}
