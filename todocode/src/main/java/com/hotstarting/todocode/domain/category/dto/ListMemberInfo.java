package com.hotstarting.todocode.domain.category.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ListMemberInfo {
    private String nickname;
    private String company;
    private String profileImage;
}
