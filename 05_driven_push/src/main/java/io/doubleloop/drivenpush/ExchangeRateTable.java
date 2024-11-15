package io.doubleloop.drivenpull;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateTable {

  public static final Map<String, Double> DEFAULT_EXCHANGE_RATES = new HashMap<String, Double>() {{
    put("GBP", 0.83);
    put("USD", 1.06);
    put("EUR", 1.00);
  }};

  private final Map<String, Double> rates;

  public ExchangeRateTable(Map<String, Double> rates) {
    this.rates = rates;
  }

  public Double rateFor(String currency) {
    Double rate = rates.get(currency);
    if (rate == null) {
      throw new IllegalArgumentException("Illegal Currency");
    }
    return rate;
  }
}
