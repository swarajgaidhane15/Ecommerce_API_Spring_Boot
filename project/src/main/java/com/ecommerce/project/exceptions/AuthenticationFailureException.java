package com.ecommerce.project.exceptions;

public class AuthenticationFailureException extends IllegalArgumentException {
  public AuthenticationFailureException(String msg) {
    super(msg);
  }
}
