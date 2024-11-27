package io.doubleloop.drivenpull.adapter;

import io.doubleloop.drivenpull.domain.BalanceOnQuery;
import io.doubleloop.drivenpull.domain.DailyBalanceService;
import io.doubleloop.drivenpull.domain.BalanceResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/balance")
public class DailyBalanceController {

  private final DailyBalanceService dailyBalanceService;

  public DailyBalanceController(DailyBalanceService dailyBalanceService) {
    this.dailyBalanceService = dailyBalanceService;
  }

  @GetMapping("/daily/{userId}")
  public ResponseEntity<BalanceResult> index(
      @PathVariable("userId") String userId,
      @RequestParam(name = "date", required = false)
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

    final var query = new BalanceOnQuery(userId, getValueOrDefault(date));
    final var balanceResult = dailyBalanceService.balanceOn(query);
    return ResponseEntity.ok(balanceResult);
  }

  private static LocalDate getValueOrDefault(LocalDate dateParam) {
    return dateParam != null ? dateParam : LocalDate.now();
  }
}
