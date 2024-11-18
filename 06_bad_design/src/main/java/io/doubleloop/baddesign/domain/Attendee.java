package io.doubleloop.baddesign.domain;

public class Attendee {
  private final String name;
  private final String email;

  public Attendee(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
}
