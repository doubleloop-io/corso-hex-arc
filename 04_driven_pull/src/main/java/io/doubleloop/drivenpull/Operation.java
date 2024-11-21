package io.doubleloop.drivenpull;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Operation {
  @Id
  private String id;

  private String userId;
  private String currency;
  private Double amount;
  private LocalDate date;

  public Operation() {
  }

  public Operation(String userId, String currency, Double amount, LocalDate date) {
    this.userId = userId;
    this.currency = currency;
    this.amount = amount;
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public String getCurrency() {
    return currency;
  }

  public Double getAmount() {
    return amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public LocalCurrencyAmount toLocalCurrency(ExchangeRateTable exchangeRateTable) {
    final var rate = exchangeRateTable.rateFor(currency);
    return new LocalCurrencyAmount(amount * rate);
  }
}
