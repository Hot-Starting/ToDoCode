package com.hotstarting.todocode.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponse {
    private String key;
    private String value;

    public static AuthResponse of(String key, String value) {
        return AuthResponse.builder()
                .key(key)
                .value(value)
                .build();
    }
}
