package com.shoppingmall.user.dto;

import com.shoppingmall.user.model.UserRoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


@Builder
public class UserResponseDTO {
  private String userId;
  private String email;
  private String nickname;
  private String address;
  private UserRoleType role;

}
