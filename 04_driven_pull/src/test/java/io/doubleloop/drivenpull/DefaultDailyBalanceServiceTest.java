package io.doubleloop.drivenpull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
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

    assertThat(result.balance).isEqualTo(BigDecimal.ZERO);
  }

  @Test
  void todayOperations() {
    operationRepository.save(new Operation("user1", "EUR", BigDecimal.valueOf(100), today));
    operationRepository.save(new Operation("user1", "EUR", BigDecimal.valueOf(100), today));

    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", today));

    assertThat(result.balance).isEqualTo(BigDecimal.valueOf(200.0));
  }

  @Test
  void yesterdayOperations() {
    operationRepository.save(new Operation("user1", "EUR", BigDecimal.valueOf(10), yesterday));
    operationRepository.save(new Operation("user1", "EUR", BigDecimal.valueOf(100), today));

    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", yesterday));

    assertThat(result.balance).isEqualTo(BigDecimal.valueOf(10.0));
  }

  @Test
  void onlyOperationsOfSelectedUser() {
    operationRepository.save(new Operation("user1", "EUR", BigDecimal.valueOf(100), today));
    operationRepository.save(new Operation("user2", "EUR", BigDecimal.valueOf(10), today));

    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", today));

    assertThat(result.balance).isEqualTo(BigDecimal.valueOf(100.0));
  }

  @Test
  void differentCurrencies() {
    operationRepository.save(new Operation("user1", "EUR", BigDecimal.valueOf(10), today));
    operationRepository.save(new Operation("user1", "USD", BigDecimal.valueOf(10), today));
    operationRepository.save(new Operation("user1", "GBP", BigDecimal.valueOf(25), today));

    final var service = new DefaultDailyBalanceService(operationRepository, exchangeProvider);

    final var result = service.balanceOn(new BalanceOnQuery("user1", today));

    assertThat(result.balance).isEqualTo(BigDecimal.valueOf(41.35));
  }
}