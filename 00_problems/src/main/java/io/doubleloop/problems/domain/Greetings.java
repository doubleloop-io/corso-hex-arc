package io.doubleloop.problems.domain;

import java.util.Objects;

public class Greetings {
  private final String name;
  private final String email;

  public Greetings(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public static Greetings from(Employee employee) {
    return new Greetings(employee.getFirstName(), employee.getEmail());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Greetings greetings = (Greetings) o;
    return Objects.equals(name, greetings.name) && Objects.equals(email, greetings.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email);
  }
}
