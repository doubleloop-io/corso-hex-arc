package io.doubleloop.driverexplicit.domain;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class User {
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
