package io.doubleloop.drivenpull;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Operation {
  @Id
  private String id;

  private String userId;
  private String currency;
  private BigDecimal amount;
  private LocalDate date;

  public Operation() {
  }

  public Operation(String userId, String currency, BigDecimal amount, LocalDate date) {
    this.userId = userId;
    this.currency = currency;
    this.amount = amount;
    this.date = date;
  }

  public LocalCurrencyAmount toLocalCurrency(ExchangeRateTable exchangeRateTable) {
    final var rate = exchangeRateTable.rateFor(currency);
    return new LocalCurrencyAmount(
        amount.multiply(BigDecimal.valueOf(rate))
    );
  }
}
