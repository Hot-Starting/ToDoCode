package com.hotstarting.todocode.domain.member.controller;

import com.hotstarting.todocode.domain.member.domain.Member;
import com.hotstarting.todocode.domain.member.service.MemberService;
import com.hotstarting.todocode.global.oauth.domain.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public Member getUser(@AuthenticationPrincipal PrincipalDetails userPrincipal) {

        // 추후 MemberDTO 따로 만들기
        Member member = memberService.getMember(userPrincipal.getName());
        return member;
    }
}
