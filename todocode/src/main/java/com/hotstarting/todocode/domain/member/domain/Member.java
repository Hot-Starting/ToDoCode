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
// 직렬화 가능한 객체로 간주함으로써 세션 클러스터링, 캐시 저장, 분산 시스템 등과 같은 상황에 대비

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    @Column(name = "member_id")
    private Integer id;

    // 이름
    @Column(nullable = false, length = 30)
    private String name;

    // 소셜 서버에서 주는 맞아
    @Column(name = "social_id", length = 64, nullable = false, unique = true)
    private String socialId;

//    // 닉네임
    @Column(unique = true, nullable = false, length = 15)
    private String nickname;

    // 이메일
    @Column(unique = true, nullable = false, length = 100)
    private String email;


    // 프로필 사진 URL
    @Column(name = "profile_image_url", nullable = false, length = 512)
    private String profileImageUrl;

//    // 비밀번호
//    @Column(name = "PASSWORD", nullable = false, length = 128)
//    private String password;

    // 소셜 서버
    @Column(name = "provider_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    // spring security용 컬럼
    @Column(nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private RoleType role;


    // 가입일시
    @Column(name = "member_created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    // 수정일시
    @Column(name = "member_updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;

    // 사용자 회원가입
    public Member(String name, String socialId, String email, String profileImageUrl, ProviderType providerType, LocalDateTime createdDate) {
        this.name = name;
        this.socialId = socialId;
        this.nickname = name;
//        this.password = "NO_PASS";
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.providerType = providerType;
        this.role = RoleType.MEMBER;
        this.createdDate = createdDate;
    }


//    public void saveRefreshToken(String refreshToken) {
//        this.refreshToken = refreshToken;
//    }

}
