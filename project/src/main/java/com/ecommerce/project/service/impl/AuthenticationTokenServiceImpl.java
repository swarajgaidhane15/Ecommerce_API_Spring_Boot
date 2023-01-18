package com.ecommerce.project.service.impl;

import com.ecommerce.project.model.AuthenticationToken;
import com.ecommerce.project.model.User;
import com.ecommerce.project.repository.AuthenticationTokenRepo;
import com.ecommerce.project.service.AuthenticationTokenService;

public class AuthenticationTokenServiceImpl implements AuthenticationTokenService {
  private AuthenticationTokenRepo tokenRepo;

  @Override
  public void saveConfirmationToken(AuthenticationToken authenticationToken) {
    tokenRepo.save(authenticationToken);
  }

  @Override
  public AuthenticationToken getToken(User user) {
    return tokenRepo.findByUser(user);
  }
}
