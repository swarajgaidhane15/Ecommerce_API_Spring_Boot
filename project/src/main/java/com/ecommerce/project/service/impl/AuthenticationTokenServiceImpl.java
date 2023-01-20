package com.ecommerce.project.service.impl;

import java.util.Objects;

import com.ecommerce.project.exceptions.AuthenticationFailureException;
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

  @Override
  public User getUser(String token) {
    AuthenticationToken authToken = tokenRepo.findByToken(token);

    return authToken.getUser();
  }

  @Override
  public void authenticateToken(String token) throws AuthenticationFailureException {
    if (Objects.isNull(token))
      throw new AuthenticationFailureException("Token not present");

    if (Objects.isNull(getUser(token)))
      throw new AuthenticationFailureException("Invalid token");
  }
}
