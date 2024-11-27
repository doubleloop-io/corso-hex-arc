package io.doubleloop.driverexplicit.domain;

public class GetUserQuery {

  private String email;

  public GetUserQuery(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }
}
