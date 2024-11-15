package io.doubleloop.drivenpull;

import org.springframework.stereotype.Component;

@Component
public class DefaultDailyBalanceService implements DailyBalanceService {

  private final OperationRepository operationRepository;
  private final ExchangeService exchangeService;

  public DefaultDailyBalanceService(
      OperationRepository operationRepository,
      ExchangeService exchangeService
  ) {
    this.operationRepository = operationRepository;
    this.exchangeService = exchangeService;
  }

  @Override
  public BalanceResult balanceOn(BalanceOnQuery query) {
    final var operations = operationRepository.findByUserIdAndDate(query.userId, query.date);
    final var exchangeTable = exchangeService.definedOn(query.date);

    final var balance = new OperationList(operations)
        .balance(exchangeTable);

    return new BalanceResult(query.userId, query.date, balance.getValue());
  }
}
