package com.hotstarting.todocode.global.jwt.reponse;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDTO {

    private String status;
    private String message;
    private Data data;

}
