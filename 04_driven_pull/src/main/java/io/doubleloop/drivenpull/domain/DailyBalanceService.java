package io.doubleloop.drivenpull.domain;

public interface DailyBalanceService {
  BalanceResult balanceOn(BalanceOnQuery query);
}
