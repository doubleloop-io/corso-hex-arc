package io.doubleloop.driverexplicit;

public class RegisterUserResult {

  private final boolean success;
  private final RegisterUserError error;

  public RegisterUserResult(boolean success, RegisterUserError error) {
    this.success = success;
    this.error = error;
  }

  public boolean isSuccess() {
    return success;
  }

  public boolean isError() {
    return error != null;
  }

  public RegisterUserError getError() {
    return error;
  }

  public static RegisterUserResult success() {
    return new RegisterUserResult(true, null);
  }

  public static RegisterUserResult error(RegisterUserError error) {
    return new RegisterUserResult(false, error);
  }
}
