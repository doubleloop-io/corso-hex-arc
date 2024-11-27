package io.doubleloop.drivenpush.domain;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class User {
  @Id
  private String id;

  private String email;
  private boolean isBlocked;

  public User() {
  }

  public User(String email, boolean isBlocked) {
    this.email = email;
    this.isBlocked = isBlocked;
  }

  public String getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public boolean isBlocked() {
    return isBlocked;
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
        ", isBlocked=" + isBlocked +
        '}';
  }

}
