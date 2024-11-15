package io.doubleloop.drivenpull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BalanceResult {
  public final String userId;
  public final LocalDate date;
  public final BigDecimal balance;

  public BalanceResult(String userId, LocalDate date, BigDecimal balance) {
    this.userId = userId;
    this.date = date;
    this.balance = balance;
  }
}
