package io.doubleloop.driverexplicit.adapter;

import io.doubleloop.driverexplicit.domain.RegisterUserCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class RegisterUserRequest {

  @NotEmpty(message = "Missing %s")
  @Email(message = "Invalid email format")
  public String email;

  @NotEmpty(message = "Missing %s")
  public String password;

  RegisterUserCommand asUser() {
    return new RegisterUserCommand(email, password);
  }
}