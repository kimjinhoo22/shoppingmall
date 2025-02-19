package com.shoppingmall.user.repository;

import com.shoppingmall.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUserId(String userId);
  User findByUserIdAndEmail(String userId, String email);
  Boolean existsByUserId(String userId);
  Boolean existsByEmail(String email);
  Boolean existsByNickname(String nickname);

}
