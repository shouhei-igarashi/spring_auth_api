package com.farmec.project.presentation.payload.response;

public class TokenResponse {
  private String token;
  private String type = "Bearer";
  private String email;
  private String role;

  public TokenResponse() {}
  
  public TokenResponse(String token, String email, String role) {
    this.token = token;
    this.email = email;
    this.role = role;
  }

  public String getAccessToken() {
    return token;
  }

  public String getTokenType() {
    return type;
  }

  public String getEmail() {
    return email;
  }

  public String getRole() {
    return role;
  }
}