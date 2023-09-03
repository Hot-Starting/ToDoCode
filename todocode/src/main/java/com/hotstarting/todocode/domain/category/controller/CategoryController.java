package com.hotstarting.todocode.domain.category.controller;

import com.hotstarting.todocode.domain.category.dto.CategoryAddRequest;
import com.hotstarting.todocode.domain.category.dto.CategoryAddResponse;
import com.hotstarting.todocode.domain.category.dto.CategoryListResponse;
import com.hotstarting.todocode.domain.category.service.CategoryService;
import com.hotstarting.todocode.global.oauth.domain.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public CategoryListResponse categoryList(@AuthenticationPrincipal PrincipalDetails memberPrincipal) {
        return categoryService.categoryList(memberPrincipal.getSocialId());
    }

    @PostMapping
    public CategoryAddResponse categoryAdd(@AuthenticationPrincipal PrincipalDetails memberPrincipal,
                                           @RequestBody CategoryAddRequest categoryAddRequest) {

        return categoryService.categoryAdd(memberPrincipal.getSocialId(), categoryAddRequest);
    }
}
