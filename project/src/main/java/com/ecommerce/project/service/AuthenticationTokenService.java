package com.ecommerce.project.service;

import org.springframework.stereotype.Service;

import com.ecommerce.project.model.AuthenticationToken;
import com.ecommerce.project.model.User;

@Service
public interface AuthenticationTokenService {

  void saveConfirmationToken(AuthenticationToken authenticationToken);

  AuthenticationToken getToken(User user);

  void authenticateToken(String token);

  User getUser(String token);
}
