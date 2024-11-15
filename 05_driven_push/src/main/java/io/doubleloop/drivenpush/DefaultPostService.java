package io.doubleloop.drivenpush;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.json.bind.Jsonb;

@Component
public class DefaultPostService implements PostService {

  public static final String exchangeName = "social_network";

  private final UserRepository userRepository;
  private final RabbitTemplate rabbitTemplate;
  private final Jsonb jsonb;

  public DefaultPostService(UserRepository userRepository, RabbitTemplate rabbitTemplate, Jsonb jsonb) {
    this.userRepository = userRepository;
    this.rabbitTemplate = rabbitTemplate;
    this.jsonb = jsonb;
  }

  @Override
  public void postMessage(PostMessageCommand command) {
    final var found = userRepository.findById(command.getUserId());
    if (found.isEmpty()) {
      throw new IllegalArgumentException("User not found");
    }
    final var user = found.get();
    if (user.isBlocked()) {
      throw new IllegalArgumentException("User is blocked");
    }

    rabbitTemplate.convertAndSend(exchangeName, "posts.%s.new".formatted(command.getUserId()), jsonb.toJson(command));
  }
}
