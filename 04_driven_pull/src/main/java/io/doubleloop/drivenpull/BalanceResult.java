package io.doubleloop.drivenpull;

import java.time.LocalDate;

public class BalanceResult {
  public final String userId;
  public final LocalDate date;
  public final Double balance;

  public BalanceResult(String userId, LocalDate date, Double balance) {
    this.userId = userId;
    this.date = date;
    this.balance = balance;
  }
}
