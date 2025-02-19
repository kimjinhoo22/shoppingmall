package com.shoppingmall.config.security;


import com.shoppingmall.oauth2.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private CustomUserDetailsService userDetailsService;
  private CustomAuthenticationFailureHandler failureHandler;
  private CustomOAuth2UserService customOAuth2UserService;

  public SecurityConfig(CustomUserDetailsService userDetailsService, CustomAuthenticationFailureHandler failureHandler, CustomOAuth2UserService customOAuth2UserService) {
      this.userDetailsService = userDetailsService;
      this.failureHandler = failureHandler;
      this.customOAuth2UserService = customOAuth2UserService;
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
                // 인증 안하고 접근할 수 있는 경로
            .requestMatchers("/", "/home", "/login", "/login/oauth2/**", "/logout", "/register/**" ,
                    "/index.html" , "/find/**" , "/board/**" , "/information"  ).permitAll()
            .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                // 그 외 모든 경로는 인증 필요.
            .anyRequest().authenticated())
                //폼 로그인 설정 시작
            .formLogin(form -> form
                    //로그인 페이지는 /login 경로로 설정
                .loginPage("/login")
                    //로그인 요청 경로는 /login/process
                    .loginProcessingUrl("/login/process")
                    // 성공시 /home 으로 리다이렉트
                    .defaultSuccessUrl("/home" , true)
                    // 실패시 실패 핸들러호출
                    .failureHandler(failureHandler))
            // 소셜 로그인 설정 시작
            .oauth2Login(oauth2 -> oauth2
                    // 소셜 로그인 페이지도 /login 경로로 설정
                            .loginPage("/login")
                    // 성공시 /home으로 리다이렉트
                            .defaultSuccessUrl("/home" , true)
                            .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2UserService)))
        .logout(logout -> logout
            .logoutSuccessUrl("/home")
            .permitAll());

        return http.build();
  }


}
