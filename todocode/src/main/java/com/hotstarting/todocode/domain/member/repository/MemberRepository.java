package com.hotstarting.todocode.domain.member.repository;

import com.hotstarting.todocode.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByName(String name);
    Member findByEmail(String email);
    Member findBySocialId(String socialId);

}
