package com.farmec.project.presentation.payload.response;

public class JwtToken {
  private String token;
  private String type = "Bearer";
  private String email;
  private String role;

  public JwtToken(String accessToken, String email, String role) {
    this.token = accessToken;
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

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return role;
  }
}