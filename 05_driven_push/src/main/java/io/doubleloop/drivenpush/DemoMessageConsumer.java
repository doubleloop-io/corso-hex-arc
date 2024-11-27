package io.doubleloop.drivenpush;

import io.doubleloop.drivenpush.domain.PostMessageCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.json.bind.Jsonb;

@Component
public class DemoMessageConsumer {

  private static final Logger log = LoggerFactory.getLogger(DemoMessageConsumer.class);

  public static final String queueName = "users_posts";

  private final Jsonb jsonb;

  private PostMessageCommand lastPostMessage;

  public DemoMessageConsumer(Jsonb jsonb) {
    this.jsonb = jsonb;
  }

  public void receiveMessage(String message) {
    lastPostMessage = jsonb.fromJson(message, PostMessageCommand.class);
    log.info("Received: {}", lastPostMessage);
  }

  public PostMessageCommand getLastPostMessage() {
    return lastPostMessage;
  }

  public void resetLastPostMessage() {
    lastPostMessage = null;
  }
}
