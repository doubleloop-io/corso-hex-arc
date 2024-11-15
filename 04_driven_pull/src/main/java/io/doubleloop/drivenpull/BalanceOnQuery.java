package io.doubleloop.drivenpull;

import java.time.LocalDate;

public class BalanceOnQuery {
  public final String userId;
  public final LocalDate date;

  public BalanceOnQuery(String userId, LocalDate date) {
    this.userId = userId;
    this.date = date;
  }
}
