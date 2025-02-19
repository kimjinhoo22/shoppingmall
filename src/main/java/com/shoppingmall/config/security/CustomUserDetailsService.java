package com.shoppingmall.config.security;

import com.shoppingmall.user.model.User;
import com.shoppingmall.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// db로부터 유저의 정보를 불러와 인증 공급자 (AuthenticationProvider) 에게 데이터를 반환하는 역할
@Service
public class CustomUserDetailsService implements UserDetailsService {
  private UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String userId) {
    System.out.println("Attempting to load user: " + userId);
    User user = userRepository.findByUserId(userId);

    if (user == null) {
      throw new UsernameNotFoundException("User not found: " + userId);
    }

    return new CustomUserDetails(user);
  }
}
