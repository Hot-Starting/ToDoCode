package com.hotstarting.todocode.domain.category.dto;

import com.hotstarting.todocode.domain.category.domain.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryAddResponse {
    private Integer categoryId;

    public static CategoryAddResponse ofCategoryAddResponse(Category category) {
        return CategoryAddResponse.builder()
                .categoryId(category.getId())
                .build();
    }
}
