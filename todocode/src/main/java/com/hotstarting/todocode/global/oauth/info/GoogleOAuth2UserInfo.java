package com.hotstarting.todocode.global.oauth.info;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class GoogleOAuth2UserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes;

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getId() {
        String id = (String) attributes.get("sub");

        log.debug("GoogleUserInfo - getId() : {}", id);

        return id;
    }

    @Override
    public String getName() {
        String name = (String) attributes.get("name");

        log.debug("GoogleUserInfo - getName() : {}", name);

        return name;
    }

    @Override
    public String getEmail() {
        String email = (String) attributes.get("email");

        log.debug("google에 등록된 이메일 : {}", email);

        return email;
    }

    @Override
    public String getImageUrl() {
        String imageUrl = (String) attributes.get("picture");

        log.debug("google에 등록된 이미지 URL : {}", imageUrl);

        return imageUrl;
    }
}
