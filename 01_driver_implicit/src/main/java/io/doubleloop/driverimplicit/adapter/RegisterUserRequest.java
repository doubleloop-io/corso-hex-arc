package io.doubleloop.driverimplicit.adapter;

import io.doubleloop.driverimplicit.domain.RegisterBusinessUserCommand;
import io.doubleloop.driverimplicit.domain.RegisterUserCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@PivaRequiredIfBusiness
public class RegisterUserRequest {

  @NotEmpty(message = "Missing %s")
  @Email(message = "Invalid email format")
  public String email;

  @NotEmpty(message = "Missing %s")
  public String password;

  public boolean isBusiness;
  public String PIVA;

  RegisterBusinessUserCommand asBusinessUser() {
    return new RegisterBusinessUserCommand(email, password, PIVA);
  }

  RegisterUserCommand asUser() {
    return new RegisterUserCommand(email, password);
  }
}
