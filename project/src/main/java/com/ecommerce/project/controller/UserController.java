package com.ecommerce.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.dto.auth.ResponseDto;
import com.ecommerce.project.dto.auth.SignInDto;
import com.ecommerce.project.dto.auth.SignInResponseDto;
import com.ecommerce.project.dto.auth.SignupDto;
import com.ecommerce.project.service.UserService;

@RestController
@RequestMapping(name = "/auth")
public class UserController {
  @Autowired
  UserService userService;

  @PostMapping("/signup")
  public ResponseDto signup(@RequestBody SignupDto body) {
    return userService.signup(body);
  }

  @PostMapping("/signin")
  public SignInResponseDto signin(@RequestBody SignInDto body) {
    return userService.signin(body);
  }
}
