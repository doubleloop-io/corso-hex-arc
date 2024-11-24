package io.doubleloop.problems;

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
}
