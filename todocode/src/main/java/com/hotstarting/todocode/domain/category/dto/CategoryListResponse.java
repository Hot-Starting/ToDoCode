package com.hotstarting.todocode.domain.category.dto;

import com.hotstarting.todocode.domain.category.domain.Category;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CategoryListResponse {
    private List<Category> categoryList;
}
