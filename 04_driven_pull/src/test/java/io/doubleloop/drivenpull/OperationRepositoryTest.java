package io.doubleloop.drivenpull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@Testcontainers
class OperationRepositoryTest {

  @Container
  @ServiceConnection
  private static MongoDBContainer container = new MongoDBContainer("mongo:latest");

  @Autowired
  private OperationRepository repository;

  @BeforeEach
  void setUp() {
    repository.deleteAll();
  }

  @Test
  void findByUserIdAndDateMatchOne() {
    repository.save(new Operation("user1", "EUR", 10.0, LocalDate.of(2024, 11, 10)));
    repository.save(new Operation("user1", "EUR", -10.0, LocalDate.of(2024, 11, 11)));
    repository.save(new Operation("user2", "EUR", 10.0, LocalDate.of(2024, 11, 10)));

    final var result = repository.findByUserIdAndDate("user1", LocalDate.of(2024, 11, 10));

    assertThat(result.size()).isEqualTo(1);
    assertThat(result.get(0).getCurrency()).isEqualTo("EUR");
    assertThat(result.get(0).getAmount()).isEqualTo(10.0);
  }

  @Test
  void findByUserIdAndDateMatchMany() {
    repository.save(new Operation("user1", "EUR", 10.0, LocalDate.of(2024, 11, 10)));
    repository.save(new Operation("user1", "EUR", -10.0, LocalDate.of(2024, 11, 10)));
    repository.save(new Operation("user2", "EUR", 10.0, LocalDate.of(2024, 11, 10)));

    final var result = repository.findByUserIdAndDate("user1", LocalDate.of(2024, 11, 10));

    assertThat(result.size()).isEqualTo(2);
  }

  @Test
  void findByUserIdAndDateNoMatch() {
    repository.save(new Operation("user1", "EUR", 10.0, LocalDate.of(2024, 11, 10)));
    repository.save(new Operation("user2", "EUR", 10.0, LocalDate.of(2024, 11, 10)));

    final var result = repository.findByUserIdAndDate("user3", LocalDate.of(2024, 11, 10));

    assertThat(result.size()).isEqualTo(0);
  }
}