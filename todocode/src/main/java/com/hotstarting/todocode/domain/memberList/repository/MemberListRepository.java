package com.hotstarting.todocode.domain.memberList.repository;

import com.hotstarting.todocode.domain.memberList.domain.MemberList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberListRepository extends JpaRepository<MemberList, Integer> {
}
