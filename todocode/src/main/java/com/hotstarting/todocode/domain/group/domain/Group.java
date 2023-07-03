package com.hotstarting.todocode.domain.group.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "group")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Group {

    // PK
    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Integer id;

    // 그룹이름
    @Column(nullable = false, length = 30)
    private String group_name;



    // 생성일시
    @Column(name = "group_created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    // 수정일시
    @Column(name = "group_updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;
}