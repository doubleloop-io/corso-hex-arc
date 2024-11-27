package io.doubleloop.driverimplicit.domain;

public class RegisterBusinessUserCommand {

  private String email;
  private String password;
  private String PIVA;

  public RegisterBusinessUserCommand(String email, String password, String PIVA) {
    this.email = email;
    this.password = password;
    this.PIVA = PIVA;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getPIVA() {
    return PIVA;
  }
}
