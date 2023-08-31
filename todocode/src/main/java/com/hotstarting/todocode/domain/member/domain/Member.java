package com.hotstarting.todocode.domain.member.domain;

import com.hotstarting.todocode.global.oauth.domain.ProviderType;
import com.hotstarting.todocode.global.oauth.domain.RoleType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "member")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    @Column(name = "member_id")
    private Integer id;

    // 이름
    @Column(nullable = false, length = 30)
    private String name;

    // 소속회사
    private String company;

    // 프로필 이미지
    @Column(name = "profile_image_url", nullable = false, length = 512)
    private String profileImageUrl;

    // 소셜 아이디
    @Column(name = "social_id", length = 64, nullable = false, unique = true)
    private String socialId;

    // spring security용 컬럼
    @Column(nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    // 활성화 여부
    @Column(name = "is_active")
    private Boolean isActive;

    // 가입일시
    @Column(name = "member_created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    // 수정일시
    @Column(name = "member_updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;

    // 사용자 회원가입
    public Member(String name, String profileImageUrl, String socialId, LocalDateTime createdDate) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.socialId = socialId;
        this.role = RoleType.MEMBER;
        this.isActive = true;
        this.createdDate = createdDate;
    }
}
