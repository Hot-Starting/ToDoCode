package com.hotstarting.todocode.domain.task.controller;

import com.hotstarting.todocode.domain.category.dto.CategoryAddRequest;
import com.hotstarting.todocode.domain.category.dto.CategoryAddResponse;
import com.hotstarting.todocode.domain.task.dto.TaskAddRequest;
import com.hotstarting.todocode.domain.task.dto.TaskAddResponse;
import com.hotstarting.todocode.domain.task.service.TaskService;
import com.hotstarting.todocode.global.oauth.domain.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskAddResponse categoryAdd(@AuthenticationPrincipal PrincipalDetails memberPrincipal,
                                       @RequestBody TaskAddRequest taskAddRequest) {

        return taskService.taskAdd(memberPrincipal.getSocialId(), taskAddRequest);
    }
}
