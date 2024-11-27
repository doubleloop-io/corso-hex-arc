package io.doubleloop.drivenpush.adapter;

import io.doubleloop.drivenpush.domain.PostMessageCommand;
import io.doubleloop.drivenpush.domain.PostNotifier;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.json.bind.Jsonb;

@Component
public class SpringRabbitMqPostNotifier implements PostNotifier {
  public static final String exchangeName = "social_network";

  private final RabbitTemplate rabbitTemplate;
  private final Jsonb jsonb;

  public SpringRabbitMqPostNotifier(RabbitTemplate rabbitTemplate, Jsonb jsonb) {
    this.rabbitTemplate = rabbitTemplate;
    this.jsonb = jsonb;
  }

  @Override
  public void postMessage(PostMessageCommand command) {
    final var message = jsonb.toJson(command);
    rabbitTemplate.convertAndSend(exchangeName, "posts.%s.new".formatted(command.getUserId()), message);
  }
}
