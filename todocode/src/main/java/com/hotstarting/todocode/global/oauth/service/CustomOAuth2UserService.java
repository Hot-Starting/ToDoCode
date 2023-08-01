package com.hotstarting.todocode.global.oauth.service;

import com.hotstarting.todocode.domain.member.domain.Member;
import com.hotstarting.todocode.domain.member.repository.MemberRepository;
import com.hotstarting.todocode.global.config.GithubEmail;
import com.hotstarting.todocode.global.config.WebClientConfig;
import com.hotstarting.todocode.global.exception.OAuthProviderMissMatchException;
import com.hotstarting.todocode.global.oauth.domain.PrincipalDetails;
import com.hotstarting.todocode.global.oauth.domain.ProviderType;
import com.hotstarting.todocode.global.oauth.info.OAuth2UserInfo;
import com.hotstarting.todocode.global.oauth.info.OAuth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    private final WebClientConfig webClientConfig;

    public Mono<String> getEmailFromGithub(String accessToken) {
        return webClientConfig.webClient()
                .get()
                .uri("https://api.github.com/user/emails")
                .header("Authorization", "token " + accessToken)
                .retrieve()
                .bodyToMono(GithubEmail[].class)
                .map(items -> Arrays.stream(items)
                        .filter(GithubEmail::isPrimary)
                        .findFirst()
                        .map(GithubEmail::getEmail)
                        .orElseThrow(() -> new RuntimeException("Primary email not found")));
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.debug("소셜서버가 사용자 정보 넘겨줌");


        OAuth2User user = super.loadUser(userRequest);

        try {
            return this.process(userRequest, user);
        } catch (AuthenticationException ex) {
            log.error(ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {

        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
        log.info("1");
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
        log.info("2");
        log.info(userInfo.getId());
        Member savedMember = memberRepository.findBySocialId(userInfo.getId());
        log.info("3");
//        String nickname = "guest" + (memberRepository.count() + 1);

        if (savedMember != null) {
            log.debug("구글로 로그인을 한 적이 있는 member입니다.");

            if (providerType != savedMember.getProviderType()) {
                log.debug("당신이 가입한 providerType : {}", savedMember.getProviderType().toString());
                throw new OAuthProviderMissMatchException(
                        "Looks like you're signed up with " + providerType +
                                " account. Please use your " + savedMember.getProviderType() + " account to login."
                );
            }
        } else {
            log.debug("소셜 로그인 최초입니다.");

            String userToken = userRequest.getAccessToken().getTokenValue();


            Mono<String> emailMono = getEmailFromGithub(userToken);
            String userEmail = emailMono.block();
            log.info(userEmail);
            savedMember = createUser(userInfo, providerType,userEmail);
        }

        return PrincipalDetails.create(savedMember, user.getAttributes());
    }

    private Member createUser(OAuth2UserInfo userInfo, ProviderType providerType, String userEmail) {
        LocalDateTime createdDate = LocalDateTime.now();
        Member member = new Member(
                userInfo.getName(),
                userInfo.getId(),
                userEmail,
                userInfo.getImageUrl(),
                providerType,
                createdDate
        );

        return memberRepository.saveAndFlush(member);
    }

    private Member updateUser(Member member, OAuth2UserInfo userInfo) {
        if (userInfo.getName() != null && !member.getName().equals(userInfo.getName())) {
            member.setName(userInfo.getName());
        }

        if (userInfo.getImageUrl() != null && !member.getProfileImageUrl().equals(userInfo.getImageUrl())) {
            member.setProfileImageUrl(userInfo.getImageUrl());
        }

        return member;
    }
}
