package io.doubleloop.drivenpull;

import org.springframework.stereotype.Service;

@Service
public class DefaultDailyBalanceService implements DailyBalanceService {

  private final OperationRepository operationRepository;
  private final ExchangeProvider exchangeProvider;

  public DefaultDailyBalanceService(
      OperationRepository operationRepository,
      ExchangeProvider exchangeProvider
  ) {
    this.operationRepository = operationRepository;
    this.exchangeProvider = exchangeProvider;
  }

  @Override
  public BalanceResult balanceOn(BalanceOnQuery query) {
    final var operations = operationRepository.findByUserIdAndDate(query.userId, query.date);
    final var exchangeTable = exchangeProvider.definedOn(query.date);

    final var balance = new OperationList(operations)
        .balance(exchangeTable);

    return new BalanceResult(query.userId, query.date, balance.getValue());
  }
}
