package com.shoppingmall.user.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {

        @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{3,20}$" ,  message = "아이디 : 영문, 숫자로 4~20자여야 합니다." )
        private String userId;
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$" , message = "이메일 : 이메일 형식을 올바르게 입력해주세요.")
        private String email;
        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,10}$", message = "닉네임 : 닉네임은 특수문자를 제외한 2~10자로 입력해주세요.")
        private String nickname;
        @NotBlank(message = "주소 : 주소를 검색해주세요")
        private String address;

}
