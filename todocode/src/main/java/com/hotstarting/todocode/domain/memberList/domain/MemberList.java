package com.hotstarting.todocode.domain.memberList.domain;

import com.hotstarting.todocode.domain.category.domain.Category;
import com.hotstarting.todocode.domain.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "member_list")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberList {

    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    @Column(name = "member_list_id")
    private Integer id;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static MemberList of(Member member, Category category) {
        return MemberList.builder()
                .member(member)
                .category(category)
                .build();
    }
}
