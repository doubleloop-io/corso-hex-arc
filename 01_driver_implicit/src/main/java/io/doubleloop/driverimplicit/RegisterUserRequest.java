package io.doubleloop.driverimplicit;

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
    // TODO: create a valid Command instance
    throw new NotImplementedException();
  }

  RegisterUserCommand asUser() {
    // TODO: create a valid Command instance
    throw new NotImplementedException();
  }
}
