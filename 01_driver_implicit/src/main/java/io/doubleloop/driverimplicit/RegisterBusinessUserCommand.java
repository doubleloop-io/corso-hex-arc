package io.doubleloop.driverimplicit;

public class RegisterBusinessUserCommand {

  public final String email;
  public final String password;
  public final String PIVA;

  public RegisterBusinessUserCommand(String email, String password, String PIVA) {
    this.email = email;
    this.password = password;
    this.PIVA = PIVA;
  }
}
