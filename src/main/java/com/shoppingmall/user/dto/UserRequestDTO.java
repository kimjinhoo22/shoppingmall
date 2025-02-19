package com.shoppingmall.user.dto;


import com.shoppingmall.user.model.User;
import com.shoppingmall.user.model.UserRoleType;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequestDTO {

  @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{3,20}$" , message = "아이디 : 영문, 숫자로 4~20자여야 합니다." )
  private String userId;
  @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[A-Za-z\\d!@#$%^&*()_+]{8,20}$", message = "비밀번호 : 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요." )
  private String password;
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$" , message = "이메일 : 이메일 형식을 올바르게 입력해주세요.")
  private String email;
  @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,10}$", message = "닉네임 : 닉네임은 특수문자를 제외한 2~10자로 입력해주세요.")
  private String nickname;
  @NotBlank(message = "질문 : 보안 질문을 선택해주세요.")
  private String question;
  @Pattern(regexp = "^[가-힣]{2,20}$" , message="답변 : 답변을 1~20자로 입력해주세요." )
  private String answer;
  @NotBlank(message = "주소 : 주소를 검색해주세요")
  private String address;

  /* DTO -> Entity */
  public User toEntity() {

    return User.builder()
        .userId(userId)
        .password(password)
        .email(email)
        .nickname(nickname)
        .question(question)
        .answer(answer)
        .address(address)
        .role(UserRoleType.USER)
        .build();
  }
}
