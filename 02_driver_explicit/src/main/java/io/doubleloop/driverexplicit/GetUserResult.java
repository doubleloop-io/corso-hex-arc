package io.doubleloop.driverexplicit;

public class GetUserResult {

  private final boolean isSuccess;
  private final GetUserError error;
  private final String id;
  private final String email;

  public GetUserResult(boolean isSuccess, GetUserError error, String id, String email) {
    this.isSuccess = isSuccess;
    this.error = error;
    this.id = id;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public static GetUserResult success(User user) {
    return new GetUserResult(true, null, user.getId(), user.getEmail());
  }

  public static GetUserResult notFound() {
    return new GetUserResult(false, GetUserError.NOT_FOUND, null, null);
  }
}
