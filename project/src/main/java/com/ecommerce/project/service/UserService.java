package com.ecommerce.project.service;

import org.springframework.stereotype.Service;

import com.ecommerce.project.dto.auth.ResponseDto;
import com.ecommerce.project.dto.auth.SignInDto;
import com.ecommerce.project.dto.auth.SignInResponseDto;
import com.ecommerce.project.dto.auth.SignupDto;

import jakarta.transaction.Transactional;

@Service
public interface UserService {

  @Transactional
  ResponseDto signup(SignupDto body);

  SignInResponseDto signin(SignInDto body);
}
