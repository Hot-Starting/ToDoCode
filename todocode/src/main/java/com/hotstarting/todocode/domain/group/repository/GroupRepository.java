package com.hotstarting.todocode.domain.group.repository;

import com.hotstarting.todocode.domain.group.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
