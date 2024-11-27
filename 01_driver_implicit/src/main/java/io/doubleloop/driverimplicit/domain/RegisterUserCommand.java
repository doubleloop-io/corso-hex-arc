package io.doubleloop.driverimplicit.domain;

public class RegisterUserCommand {

  private String email;
  private String password;

  public RegisterUserCommand(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
