package io.doubleloop.bank.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Account {
  @Id
  private String id;

  private String firstName;
  private String lastName;
  private String CF;
  private LocalDate dateOfBirth;
  private Double balance;
  private LocalDate openingDate;

  public Account() {
  }

  public Account(String firstName, String lastName, String CF, LocalDate dateOfBirth, Double balance, LocalDate openingDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.CF = CF;
    this.dateOfBirth = dateOfBirth;
    this.balance = balance;
    this.openingDate = openingDate;
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getCF() {
    return CF;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public Double getBalance() {
    return balance;
  }

  public LocalDate getOpeningDate() {
    return openingDate;
  }

  public void deposit(Double amount) {
    balance += amount;
  }

  public void withdraw(Double amount) {
    balance -= amount;
  }
}
