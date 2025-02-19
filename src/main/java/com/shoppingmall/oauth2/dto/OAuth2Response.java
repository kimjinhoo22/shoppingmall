package com.shoppingmall.oauth2.dto;

// 각 소셜 Response 의 구조를 정의하는 인터페이스
public interface OAuth2Response {

    // 제공자 이름 ( 네이버 혹은 구글 )
    String getProvider();
    // 제공자가 user에게 부여해주는 고유번호
    String getProviderId();
    // 이메일
    String getEmail();
    // 사용자의 실명
    String getName();
}
