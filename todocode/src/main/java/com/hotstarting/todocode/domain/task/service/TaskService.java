package com.hotstarting.todocode.domain.task.service;

import com.hotstarting.todocode.domain.member.repository.MemberRepository;
import com.hotstarting.todocode.domain.task.dto.TaskAddRequest;
import com.hotstarting.todocode.domain.task.dto.TaskAddResponse;
import com.hotstarting.todocode.domain.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;
    private final MemberRepository memberRepository;

    public TaskAddResponse taskAdd(String socialId, TaskAddRequest taskAddRequest) {
        return null;

    }
}
