//package com.hotstarting.todocode.domain.auth.domain;
//
//
//import com.hotstarting.todocode.domain.member.domain.Member;
//import com.hotstarting.todocode.global.oauth.domain.ProviderType;
//import com.hotstarting.todocode.global.oauth.domain.RoleType;
//import lombok.*;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "member")
//@Getter
//@Setter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class Auth {
//
//    // PK
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
//    @Column(name = "member_id")
//    private Integer id;
//
//    // FK
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    // 이메일
//    @Column(unique = true, nullable = false, length = 100)
//    private String email;
//
//    // 소셜 서버
//    @Column(name = "provider_type", nullable = false, length = 20)
//    @Enumerated(EnumType.STRING)
//    private ProviderType providerType;
//
//    // 등록일시
//    @Column(name = "auth_created_date", updatable = false)
//    @CreatedDate
//    private LocalDateTime createdDate;
//
//    // 수정일시
//    @Column(name = "auth_updated_date")
//    @LastModifiedDate
//    private LocalDateTime updatedDate;
//
//}
//
