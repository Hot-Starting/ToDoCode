package com.hotstarting.todocode.domain.category.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CategoryAddRequest {
    private String name;
    private List<String> memberEmail;
    private String emoticonUrl;
}
