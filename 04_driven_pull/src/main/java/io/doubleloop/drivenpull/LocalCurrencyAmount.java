package io.doubleloop.drivenpull;

public class LocalCurrencyAmount {

  public static final LocalCurrencyAmount ZERO = new LocalCurrencyAmount(0.0);
  private final Double value;

  public LocalCurrencyAmount(Double value) {
    this.value = value;
  }

  public LocalCurrencyAmount add(LocalCurrencyAmount other) {
    return new LocalCurrencyAmount(value + other.value);
  }

  public Double getValue() {
    return value;
  }
}
