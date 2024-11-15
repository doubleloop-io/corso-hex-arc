package io.doubleloop.drivenpull;

public interface DailyBalanceService {
  BalanceResult balanceOn(BalanceOnQuery query);
}
