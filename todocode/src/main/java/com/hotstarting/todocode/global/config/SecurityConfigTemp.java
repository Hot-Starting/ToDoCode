//package com.hotstarting.todocode.global.config;
//
//import com.hotstarting.todocode.domain.member.repository.MemberRepository;
//import com.hotstarting.todocode.domain.member.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
//import com.hotstarting.todocode.global.exception.RestAuthenticationEntryPoint;
//import com.hotstarting.todocode.global.jwt.AuthTokenProvider;
//import com.hotstarting.todocode.global.jwt.TokenAccessDeniedHandler;
//import com.hotstarting.todocode.global.jwt.TokenAuthenticationFilter;
//import com.hotstarting.todocode.global.oauth.domain.AppProperties;
//import com.hotstarting.todocode.global.oauth.handler.OAuth2AuthenticationFailureHandler;
//import com.hotstarting.todocode.global.oauth.handler.OAuth2AuthenticationSuccessHandler;
//import com.hotstarting.todocode.global.oauth.service.CustomOAuth2UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfigTemp {
//
//    private final CorsFilter corsFilter;
//    private final AppProperties appProperties;
//    private final AuthTokenProvider tokenProvider;
//    private final CustomOAuth2UserService oAuth2UserService;
//    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
//    private final MemberRepository memberRepository;
//
//    /**
//     * Spring Security 5.0 이후부터는 WebSecurityConfigurerAdapter가 deprecated 되었고,
//     * SecurityFilterChain 빈을 직접 구현하게 되었음
//     * @return
//     */
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring().mvcMatchers(
//                "/v3/api-docs/**",
//                "/swagger-ui/**",
//                "/swagger-resources/**",
//                "/swagger/**",
//                "/webjars/**'",
//                "/api/member/signup/**"
////                oauth도 추가하기
//        );
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .csrf().disable()
//                .addFilter(corsFilter)
//                .formLogin().disable()
//                .httpBasic().disable()
//                .authorizeRequests()
//                .antMatchers("/api/**").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)  //요청 헤더에 담긴 토큰을 검증하고, 해당 토큰이 유효하다면 Security Context에 인증 정보를 저장하는 작업을 수행
//                .exceptionHandling()
//                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
//                .accessDeniedHandler(tokenAccessDeniedHandler)
//                .and().build();
//    }
//
//    /*
//     * security 설정 시, 사용할 인코더 설정
//     * */
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /*
//     * 쿠키 기반 인가 Repository
//     * 인가 응답을 연계 하고 검증할 때 사용.
//     * */
//    @Bean
//    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
//        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
//    }
//
//    /*
//     * Oauth 인증 성공 핸들러
//     * */
//    @Bean
//    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
//        return new OAuth2AuthenticationSuccessHandler(
//                memberRepository,
//                tokenProvider,
//                appProperties,
//                oAuth2AuthorizationRequestBasedOnCookieRepository()
//        );
//    }
//
//    /*
//     * Oauth 인증 실패 핸들러
//     * */
//    @Bean
//    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
//        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
//    }
//
//    /*
//     * 토큰 필터 설정
//     * */
//    @Bean
//    public TokenAuthenticationFilter tokenAuthenticationFilter() {
//        return new TokenAuthenticationFilter(tokenProvider);
//    }
//}
