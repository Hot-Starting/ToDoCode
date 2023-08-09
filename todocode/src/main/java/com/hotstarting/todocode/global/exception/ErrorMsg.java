package com.hotstarting.todocode.global.exception;

public interface ErrorMsg {

    // 500 에러
    String INTERNAL_SERVER_ERROR = "내부 서버 오류 입니다.";

    // 인증
    String INVALID_ACCESS_TOKEN = "Invalid access token.";
    String INVALID_REFRESH_TOKEN = "Invalid refresh token.";
    String NOT_EXPIRED_TOKEN_YET = "토큰이 아직 만료되지 않았습니다.";
    String EXPIRED_JWT_TOKEN = "토큰 기간이 만료되었습니다.";

}
