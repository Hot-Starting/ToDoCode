package com.hotstarting.todocode.global.response;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDTO {
    private String status;
    private String message;
    private Object data;
}
