package com.shoppingmall.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


  @GetMapping("/login")
  public String loginG(){
    return "user/login";
  }
}
