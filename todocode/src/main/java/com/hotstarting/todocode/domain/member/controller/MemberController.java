package com.hotstarting.todocode.domain.member.controller;

import com.hotstarting.todocode.domain.member.domain.Member;
import com.hotstarting.todocode.domain.member.service.MemberService;
import com.hotstarting.todocode.global.oauth.domain.PrincipalDetails;
import com.hotstarting.todocode.global.response.ResponseDTO;
import com.hotstarting.todocode.global.util.Msg;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<ResponseDTO> getUser(
            @AuthenticationPrincipal PrincipalDetails userPrincipal
    ) {

        Member member = memberService.getMember(userPrincipal.getName());
        // 추후 MemberDTO 따로 만들기
        ResponseDTO responseDTO = ResponseDTO.builder().status("SUCCESS").message(Msg.SUCCESS_MEMBER_INFO).data(member).build();

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
