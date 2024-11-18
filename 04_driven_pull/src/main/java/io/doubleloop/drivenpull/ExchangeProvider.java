package io.doubleloop.drivenpull;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
public class ExchangeProvider {
  public ExchangeRateTable definedOn(LocalDate day) {
    return new ExchangeRateTable(httpGetExchangeRatesFromExternalSystem(day));
  }

  private static Map<String, Double> httpGetExchangeRatesFromExternalSystem(LocalDate day) {
    // NOTE: This is a fake implementation, it should be replaced with a real one

    return ExchangeRateTable.DEFAULT_EXCHANGE_RATES;
  }
}
