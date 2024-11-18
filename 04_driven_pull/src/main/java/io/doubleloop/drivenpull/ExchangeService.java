package io.doubleloop.drivenpull;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class ExchangeService {
  public ExchangeRateTable definedOn(LocalDate day){
    return new ExchangeRateTable(httpGetExchangeRatesFromExternalSystem(day));
  }

  private static Map<String, Double> httpGetExchangeRatesFromExternalSystem(LocalDate day) {
    return ExchangeRateTable.DEFAULT_EXCHANGE_RATES;
  }
}
