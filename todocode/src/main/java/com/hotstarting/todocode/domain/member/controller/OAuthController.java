package com.hotstarting.todocode.domain.member.controller;

import com.hotstarting.todocode.domain.member.domain.Member;
import com.hotstarting.todocode.domain.member.repository.MemberRepository;
import com.hotstarting.todocode.global.exception.CustomException;
import com.hotstarting.todocode.global.exception.ErrorMsg;
import com.hotstarting.todocode.global.jwt.AuthToken;
import com.hotstarting.todocode.global.jwt.AuthTokenProvider;
import com.hotstarting.todocode.global.oauth.domain.AppProperties;
import com.hotstarting.todocode.global.oauth.domain.RoleType;
import com.hotstarting.todocode.global.response.ResponseDTO;
import com.hotstarting.todocode.global.util.CookieUtil;
import com.hotstarting.todocode.global.util.HeaderUtil;
import com.hotstarting.todocode.global.util.Msg;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class OAuthController {

    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    private final static long THREE_DAYS_MSEC = 259200000;
    private final static String REFRESH_TOKEN = "refresh_token";


    @GetMapping("/refresh")
    public ResponseEntity<ResponseDTO> refreshToken (HttpServletRequest request, HttpServletResponse response) {
        // access token 확인
        String accessToken = HeaderUtil.getAccessToken(request);
        AuthToken authToken = tokenProvider.convertAuthToken(accessToken);
        if (!authToken.validate()) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, ErrorMsg.INVALID_ACCESS_TOKEN);
        }

        // expired access token 인지 확인
        Claims claims = authToken.getExpiredTokenClaims();
        if (claims == null) {
            throw new CustomException(HttpStatus.FORBIDDEN, ErrorMsg.NOT_EXPIRED_TOKEN_YET);
        }

        String userId = claims.getSubject();
        RoleType roleType = RoleType.of(claims.get("role", String.class));

        // refresh token
        String refreshToken = CookieUtil.getCookie(request, REFRESH_TOKEN)
                .map(Cookie::getValue)
                .orElse((null));
        AuthToken authRefreshToken = tokenProvider.convertAuthToken(refreshToken);

        // 로직이 이해 안됨 > true인데 왜 INVALID Exception?..
        if (authRefreshToken.validate()) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, ErrorMsg.INVALID_REFRESH_TOKEN);
        }

        // 후에 Redis에서 사용자 아이디로 리프레시토큰 조회해서 가져오게 끔 수정
//        String userRefreshToken = memberRepository.findBySocialId(userId).getRefreshToken();
//        if (userRefreshToken == null || userRefreshToken.isEmpty()) {
//            throw new CustomException(HttpStatus.UNAUTHORIZED, ErrorMsg.INVALID_REFRESH_TOKEN);
//        }

        Date now = new Date();
        AuthToken newAccessToken = tokenProvider.createAuthToken(
                userId,
                roleType.getCode(),
                new Date(now.getTime() + appProperties.getAuth().getTokenExpiry())
        );

        long validTime = authRefreshToken.getTokenClaims().getExpiration().getTime() - now.getTime();

        // refresh 토큰 기간이 3일 이하로 남은 경우, refresh 토큰 갱신
        if (validTime <= THREE_DAYS_MSEC) {
            // refresh 토큰 설정
            long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();

            authRefreshToken = tokenProvider.createAuthToken(
                    appProperties.getAuth().getTokenSecret(),
                    new Date(now.getTime() + refreshTokenExpiry)
            );

            // DB에 refresh 토큰 업데이트
            Member member = memberRepository.findBySocialId(userId);
//            member.saveRefreshToken(authRefreshToken.getToken());
            memberRepository.saveAndFlush(member);

            int cookieMaxAge = (int) refreshTokenExpiry / 60;
            CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
            CookieUtil.addCookie(response, REFRESH_TOKEN, authRefreshToken.getToken(), cookieMaxAge);
        }

        Map<String, String> map = new HashMap<>();
        map.put("token", newAccessToken.getToken());
        ResponseDTO responseDTO = ResponseDTO.builder().status("SUCCESS").message(Msg.SUCCESS_TOKEN_REISSUE).data(map).build();

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
