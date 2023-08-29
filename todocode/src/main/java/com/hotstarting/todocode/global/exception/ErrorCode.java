package com.hotstarting.todocode.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 500 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류 입니다."),

    // 인증
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid access token."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid refresh token."),
    NOT_EXPIRED_TOKEN_YET(HttpStatus.UNAUTHORIZED, "토큰이 아직 만료되지 않았습니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "토큰 기간이 만료되었습니다.");

    private final HttpStatus status;
    private final String message;
}
