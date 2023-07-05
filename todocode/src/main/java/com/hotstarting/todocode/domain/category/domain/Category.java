package com.hotstarting.todocode.domain.category.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Category {

    // PK
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Integer id;

    // 그룹이름
    @Column(nullable = false, length = 30)
    private String category_name;

    // 생성일시
    @Column(name = "category_created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    // 수정일시
    @Column(name = "category_updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;
}