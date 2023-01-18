package com.ecommerce.project.dto.auth;

public class SignInResponseDto {
  private String status;
  private String token;

  public String getStatus() {
    return status;
  }

  public SignInResponseDto(String status, String token) {
    this.status = status;
    this.token = token;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
