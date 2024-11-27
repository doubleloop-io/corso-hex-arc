package io.doubleloop.drivenpull.domain;

import java.util.List;

public class OperationList
{
  private final List<Operation> operations;

  public OperationList(List<Operation> operations) {
    this.operations = operations;
  }

  public LocalCurrencyAmount balance(ExchangeRateTable exchangeRateTable) {
    return operations.stream()
        .map(x -> x.toLocalCurrency(exchangeRateTable))
        .reduce(LocalCurrencyAmount.ZERO, LocalCurrencyAmount::add);
  }
}
