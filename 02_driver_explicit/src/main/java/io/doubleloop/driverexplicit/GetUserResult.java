package io.doubleloop.driverexplicit;

public class GetUserResult {

  private final User user;

  public GetUserResult(User user) {
    this.user = user;
  }

  public String getId() {
    return user.getId();
  }

  public String getEmail() {
    return user.getEmail();
  }
}
