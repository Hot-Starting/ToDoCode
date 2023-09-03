package com.hotstarting.todocode.domain.category.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ListCategoryInfo {
    private Integer id;
    private String categoryName;
    private Integer taskCnt;
    private List<ListMemberInfo> memberList;
}
