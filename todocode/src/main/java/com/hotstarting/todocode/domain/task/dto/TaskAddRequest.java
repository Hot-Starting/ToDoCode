package com.hotstarting.todocode.domain.task.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskAddRequest {
    private Integer categoryId;
    private String content;
    private String startDate;
    private String endDate;
}
