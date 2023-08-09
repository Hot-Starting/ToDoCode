package com.hotstarting.todocode.global.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    private final String status;
    private final String message;

    public ErrorResponse(String errorMsg) {
        this.status = "FAIL";
        this.message = errorMsg;
    }
}
