package io.doubleloop.driverexplicit.domain;

public class RegisterUserCommand {

  public final String email;
  public final String password;

  public RegisterUserCommand(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
