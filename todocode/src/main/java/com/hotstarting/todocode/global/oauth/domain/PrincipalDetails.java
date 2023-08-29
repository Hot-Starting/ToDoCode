package com.hotstarting.todocode.global.oauth.domain;

import com.hotstarting.todocode.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PrincipalDetails implements OAuth2User, OidcUser {
    private final String socialId;
//    private final String password;
    private final ProviderType providerType;
    private final RoleType roleType;
    private final Collection<GrantedAuthority> authorities;
    private Map<String, Object> attributes;


    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return socialId;
    }

//    public String getUsername() {
//        return socialId;
//    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public Map<String, Object> getClaims() {
        return null;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        return null;
    }

//    public static PrincipalDetails create(Member member) {
//        return new PrincipalDetails(
//                member.getSocialId(),
//                member.getPassword(),
//                member.getProviderType(),
//                RoleType.MEMBER,
//                Collections.singletonList(new SimpleGrantedAuthority(RoleType.MEMBER.getCode()))
//        );
//    }

    public static PrincipalDetails create(Member member, Map<String, Object> attributes) {
        PrincipalDetails userPrincipal = new PrincipalDetails(
                member.getSocialId(),
                member.getProviderType(),
                RoleType.MEMBER,
                Collections.singletonList(new SimpleGrantedAuthority(RoleType.MEMBER.getCode()))
        );
        userPrincipal.setAttributes(attributes);

        return userPrincipal;
    }
}
