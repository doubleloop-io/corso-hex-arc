package io.doubleloop.driverexplicit;

import io.doubleloop.driverexplicit.domain.GetUserResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@Testcontainers
class UserRepositoryTest {

  @Container
  @ServiceConnection
  private static MongoDBContainer container = new MongoDBContainer("mongo:latest");

  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
  }

  @Test
  void findByEmailMatch() {
    userRepository.save(new GetUserResult.User("foo@bar.it", "test"));
    userRepository.save(new GetUserResult.User("test@test.it", "test"));

    final var result = userRepository.findByEmail("foo@bar.it");

    assertThat(result.isPresent()).isTrue();
    assertThat(result.get().getEmail()).isEqualTo("foo@bar.it");
  }

  @Test
  void findByEmailNotMatch() {
    userRepository.save(new GetUserResult.User("test@test.it", "test"));

    final var result = userRepository.findByEmail("foo@bar.it");

    assertThat(result.isEmpty()).isTrue();
  }
}