package io.doubleloop.problems.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {

  private final LocalDate birthDate;
  private final String lastName;
  private final String firstName;
  private final String email;

  public Employee(String firstName, String lastName, String birthDate, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    this.email = email;
  }

  public boolean isBirthday(LocalDate today) {
    return isSameDay(today, birthDate);
  }

  private boolean isSameDay(LocalDate today, LocalDate birthDate) {
    return today.getMonthValue() == birthDate.getMonthValue()
        && today.getDayOfMonth() == birthDate.getDayOfMonth();
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstName() {
    return firstName;
  }
}
