package com.shoppingmall.user.model;

import com.shoppingmall.user.dto.UserResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true, nullable = false)
  private String userId;
  @Column(length = 60, nullable = false)
  private String password;
  @Column(unique = true, nullable = false )
  private String email;
  @Column(unique = true, nullable = false)
  private String nickname;
  private String question;
  private String answer;
  private String address;
  @Enumerated(EnumType.STRING)
  private UserRoleType role;


  public UserResponseDTO toDTO(){
    return UserResponseDTO.builder()
        .userId(userId)
        .email(email)
        .nickname(nickname)
        .address(address)
        .build();
  }
}
