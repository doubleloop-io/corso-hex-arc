package io.doubleloop.driverexplicit.domain;

import org.springframework.data.annotation.Id;

import java.util.Objects;

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

  public static class RegisterUserResult {
  
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

  public static class User {
    @Id
    private String id;
  
    private String email;
    private String passwordHash;
  
    public User() {
    }
  
    public User(String email, String passwordHash) {
      this.email = email;
      this.passwordHash = passwordHash;
    }
  
    public String getId() {
      return id;
    }
  
    public String getEmail() {
      return email;
    }
  
    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return Objects.equals(id, user.id);
    }
  
    @Override
    public int hashCode() {
      return Objects.hashCode(id);
    }
  
    @Override
    public String toString() {
      return "User{" +
          "id='" + id + '\'' +
          ", email='" + email + '\'' +
          '}';
    }
  }
}
