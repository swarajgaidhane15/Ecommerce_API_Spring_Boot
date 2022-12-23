package com.ecommerce.project.common;

import java.time.LocalDateTime;

public class ApiResponse {
  private final boolean success;
  private final String message;
  private final Object model;

  public ApiResponse(boolean success, String message, Object model) {
    this.success = success;
    this.message = message;
    this.model = model;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }

  public Object getModel() {
    return model;
  }

  public String getTimeStamp() {
    return LocalDateTime.now().toString();
  }
}
