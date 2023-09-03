package com.hotstarting.todocode.domain.task.dto;

import com.hotstarting.todocode.domain.category.dto.CategoryAddResponse;
import com.hotstarting.todocode.domain.task.domain.Task;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskAddResponse {
    private Integer taskId;

    public static CategoryAddResponse ofTaskAddResponse(Task task) {
        return CategoryAddResponse.builder()
                .categoryId(task.getId())
                .build();
    }
}
