package com.hotstarting.todocode.global.oauth.info;

import com.hotstarting.todocode.global.oauth.domain.ProviderType;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE:
                return new GoogleOAuth2UserInfo(attributes);
            default:
                throw new IllegalArgumentException("유효하지 않은 Provider Type 입니다.");
        }
    }

}
