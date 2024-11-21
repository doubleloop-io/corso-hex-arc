package io.doubleloop.drivenpull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class DefaultDailyBalanceServiceTest {
  @Container
  @ServiceConnection
  private static MongoDBContainer container = new MongoDBContainer("mongo:latest");

  @Autowired
  private OperationRepository operationRepository;
  @Autowired
  private ExchangeProvider exchangeProvider;

  private static final LocalDate today = LocalDate.now();
  private static final LocalDate yesterday = LocalDate.now().minusDays(1);

  @BeforeEach
  void setUp() {
    operationRepository.deleteAll();
  }

  @Test
  void noOperations() {
    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", today));

    assertThat(result.balance).isEqualTo(0);
  }

  @Test
  void todayOperations() {
    operationRepository.save(new Operation("user1", "EUR", 100.0, today));
    operationRepository.save(new Operation("user1", "EUR", 100.0, today));

    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", today));

    assertThat(result.balance).isEqualTo(200.0);
  }

  @Test
  void yesterdayOperations() {
    operationRepository.save(new Operation("user1", "EUR", 10.0, yesterday));
    operationRepository.save(new Operation("user1", "EUR", 100.0, today));

    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", yesterday));

    assertThat(result.balance).isEqualTo(10.0);
  }

  @Test
  void onlyOperationsOfSelectedUser() {
    operationRepository.save(new Operation("user1", "EUR", 100.0, today));
    operationRepository.save(new Operation("user2", "EUR", 10.0, today));

    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", today));

    assertThat(result.balance).isEqualTo(100.0);
  }

  @Test
  void differentCurrencies() {
    operationRepository.save(new Operation("user1", "EUR", 10.0, today));
    operationRepository.save(new Operation("user1", "USD", 10.0, today));
    operationRepository.save(new Operation("user1", "GBP", 25.0, today));

    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", today));

    assertThat(result.balance).isEqualTo(41.35);
  }
}