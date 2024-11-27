package io.doubleloop.drivenpull;

import io.doubleloop.drivenpull.domain.BalanceOnQuery;
import io.doubleloop.drivenpull.domain.DefaultDailyBalanceService;
import io.doubleloop.drivenpull.domain.ExchangeProvider;
import io.doubleloop.drivenpull.domain.ExchangeRateTable;
import io.doubleloop.drivenpull.domain.Operation;
import io.doubleloop.drivenpull.domain.OperationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DefaultDailyBalanceServiceTest {

  private final OperationRepository operationRepository = Mockito.mock(OperationRepository.class);
  private final ExchangeProvider exchangeProvider = Mockito.mock(ExchangeProvider.class);

  private static final LocalDate today = LocalDate.now();
  private static final LocalDate yesterday = LocalDate.now().minusDays(1);

  @Test
  void noOperations() {
    when(operationRepository.findByUserIdAndDate(any(), any()))
        .thenReturn(List.of());
    when(exchangeProvider.definedOn(any()))
        .thenReturn(new ExchangeRateTable(ExchangeRateTable.DEFAULT_EXCHANGE_RATES));

    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", today));

    assertThat(result.balance).isEqualTo(0);
  }

  @Test
  void todayOperations() {
    when(operationRepository.findByUserIdAndDate(any(), any()))
        .thenReturn(List.of(new Operation("user1", "EUR", 100.0, today),
            new Operation("user1", "EUR", 100.0, today)));
    when(exchangeProvider.definedOn(any()))
        .thenReturn(new ExchangeRateTable(ExchangeRateTable.DEFAULT_EXCHANGE_RATES));

    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", today));

    assertThat(result.balance).isEqualTo(200.0);
  }

  @Test
  void yesterdayOperations() {
    when(operationRepository.findByUserIdAndDate(any(), any()))
        .thenReturn(List.of(new Operation("user1", "EUR", 10.0, yesterday)));
    when(exchangeProvider.definedOn(any()))
        .thenReturn(new ExchangeRateTable(ExchangeRateTable.DEFAULT_EXCHANGE_RATES));

    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", yesterday));

    assertThat(result.balance).isEqualTo(10.0);
  }

  @Test
  void onlyOperationsOfSelectedUser() {
    when(operationRepository.findByUserIdAndDate(any(), any()))
        .thenReturn(List.of(new Operation("user1", "EUR", 100.0, today)));
    when(exchangeProvider.definedOn(any()))
        .thenReturn(new ExchangeRateTable(ExchangeRateTable.DEFAULT_EXCHANGE_RATES));

    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", today));

    assertThat(result.balance).isEqualTo(100.0);
  }

  @Test
  void differentCurrencies() {
    when(operationRepository.findByUserIdAndDate(any(), any()))
        .thenReturn(List.of(
            new Operation("user1", "EUR", 10.0, today),
            new Operation("user1", "USD", 10.0, today),
            new Operation("user1", "GBP", 25.0, today)
        ));
    when(exchangeProvider.definedOn(any()))
        .thenReturn(new ExchangeRateTable(ExchangeRateTable.DEFAULT_EXCHANGE_RATES));

    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", today));

    assertThat(result.balance).isEqualTo(41.35);
  }
}