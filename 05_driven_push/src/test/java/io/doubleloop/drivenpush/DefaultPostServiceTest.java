package io.doubleloop.drivenpush;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Testcontainers
class DefaultPostServiceTest {

  @Container
  @ServiceConnection
  private static MongoDBContainer container = new MongoDBContainer("mongo:latest");

  @Container
  static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:latest")
      .withUser("guest", "guest")
      .withExposedPorts(5672);

  @Autowired
  private DemoMessageConsumer consumer;

  @Autowired
  private SpringMongoUserRepository userRepository;

  @Autowired
  private DefaultPostService postService;

  @BeforeAll
  static void setup() {
    System.setProperty("spring.rabbitmq.host", rabbitMQContainer.getHost());
    System.setProperty("spring.rabbitmq.port", rabbitMQContainer.getAmqpPort().toString());
    System.setProperty("spring.rabbitmq.username", "guest");
    System.setProperty("spring.rabbitmq.password", "guest");
  }

  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
    consumer.resetLastPostMessage();
  }

  @Test
  void userPostMessage() throws Exception {
    final var user = userRepository.save(new User("foo@bar.it", false));

    final var command = new PostMessageCommand();
    command.setUserId(user.getId());
    command.setContent("test");

    postService.postMessage(command);

    sleep(100);
    final var post = consumer.getLastPostMessage();
    assertThat(post).isEqualTo(command);
  }

  @Test
  void blockedUserPostMessage() throws Exception {
    final var user = userRepository.save(new User("foo@bar.it", true));

    final var command = new PostMessageCommand();
    command.setUserId(user.getId());
    command.setContent("test");

    assertThatThrownBy(() -> postService.postMessage(command))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void unknownUserPostMessage() throws Exception {
    final var user = userRepository.save(new User("foo@bar.it", true));

    final var command = new PostMessageCommand();
    command.setUserId("unknown");
    command.setContent("test");

    assertThatThrownBy(() -> postService.postMessage(command))
        .isInstanceOf(IllegalArgumentException.class);
  }
}