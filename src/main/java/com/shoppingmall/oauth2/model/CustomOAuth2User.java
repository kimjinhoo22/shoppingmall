package com.shoppingmall.oauth2.model;

import com.shoppingmall.oauth2.dto.OAuth2Response;
import com.shoppingmall.user.model.UserRoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final OAuth2Response oAuth2Response;

    private final UserRoleType role;

    public CustomOAuth2User(OAuth2Response oAuth2Response, UserRoleType role) {
        this.oAuth2Response = oAuth2Response;
        this.role = role;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role.name();
            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return oAuth2Response.getProvider() +  " " + oAuth2Response.getName();
    }



}
