package com.hotstarting.todocode.domain.task.domain;

import com.hotstarting.todocode.domain.category.domain.Category;
import com.hotstarting.todocode.domain.member.domain.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Task {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    @Column(name = "task_id")
    private Integer id;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

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
    @Column(name = "task_created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    // 수정일시
    @Column(name = "task_updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;
}
