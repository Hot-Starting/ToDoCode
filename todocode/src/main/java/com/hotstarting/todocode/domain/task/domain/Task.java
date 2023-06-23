package com.hotstarting.todocode.domain.task.domain;

import com.hotstarting.todocode.domain.member.domain.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Task implements Serializable {
// 직렬화 가능한 객체로 간주함으로써 세션 클러스터링, 캐시 저장, 분산 시스템 등과 같은 상황에 대비

    // PK
    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private Long id;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    // 분류
//    @Column()
//    private String category;

    // 내용
    @Column(length = 500)
    private String content;

    // 할일 시작 일시
    @Column(name = "start_date")
    private LocalDateTime startDate;

    // 할일 종료 일시
    @Column(name = "end_date")
    private LocalDateTime endDate;

    // 우선순위
    private Long priority;

    // 생성일시
    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    // 수정일시
    @Column(name = "updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;
}
