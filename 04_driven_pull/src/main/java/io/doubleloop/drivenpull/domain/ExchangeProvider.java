package io.doubleloop.drivenpull.domain;

import java.time.LocalDate;

public interface ExchangeProvider {
  ExchangeRateTable definedOn(LocalDate day);
}
