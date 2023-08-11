package com.hotstarting.todocode.domain.member.controller;

import com.hotstarting.todocode.domain.member.domain.Member;
import com.hotstarting.todocode.domain.member.service.MemberService;
import com.hotstarting.todocode.global.response.ResponseDTO;
import com.hotstarting.todocode.global.util.Msg;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseDTO getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member member = memberService.getMember(principal.getUsername());
        // 추후 MemberDTO 따로 만들기
        ResponseDTO responseDTO = ResponseDTO.builder().status("SUCCESS").message(Msg.SUCCESS_MEMBER_INFO).data(member).build();

        return responseDTO;
    }
}
