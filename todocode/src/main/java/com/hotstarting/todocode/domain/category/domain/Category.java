package com.hotstarting.todocode.domain.category.domain;

import com.hotstarting.todocode.domain.member.domain.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    @Column(name = "category_id")
    private Integer id;

    // 그룹이름
    @Column(nullable = false, length = 30, name = "category_name")
    private String categoryName;

    // 이모티콘 URL
    @Column(nullable = false, name = "emoticon_url")
    private String emoticonUrl;

    // 생성일시
    @Column(name = "category_created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    // 수정일시
    @Column(name = "category_updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;

    public static Category of(String categoryName, String emoticonUrl) {
        return Category.builder()
                .categoryName(categoryName)
                .emoticonUrl(emoticonUrl)
                .build();
    }
}