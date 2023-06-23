package com.hotstarting.todocode.domain.task.repository;

import com.hotstarting.todocode.domain.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
