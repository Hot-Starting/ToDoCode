package com.hotstarting.todocode.domain.member.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    // 닉네임
    @Column(unique = true, nullable = false, length = 15)
    private String nickname;

    // 이메일
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    // 성별
    @Column(nullable = false)
    private String gender;

    // 소셜서버
    @Column(name = "oauth_id", nullable = false, length = 15)
    private String socialServer;

    // spring security용 컬럼
    @Column(nullable = false, length = 15)
    private String role;

    // 가입일시
    @Column(name = "member_created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    // 수정일시
    @Column(name = "member_updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;

    // 닉네임 수정
    public void updateNickname(String nicknamee) {
        this.nickname = nickname;
    }
}
