package io.doubleloop.drivenpull;

import java.math.BigDecimal;

public class LocalCurrencyAmount {

  public static final LocalCurrencyAmount ZERO = new LocalCurrencyAmount(BigDecimal.ZERO);
  private final BigDecimal value;

  public LocalCurrencyAmount(BigDecimal value) {
    this.value = value;
  }

  public LocalCurrencyAmount add(LocalCurrencyAmount other) {
    return new LocalCurrencyAmount(value.add(other.value));
  }

  public BigDecimal getValue() {
    return value;
  }
}
