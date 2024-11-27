package io.doubleloop.drivenpush.adapter;

import io.doubleloop.drivenpush.domain.PostMessageCommand;
import jakarta.validation.constraints.NotEmpty;

public class PostMessageRequest {

  @NotEmpty(message = "Missing %s")
  public String userId;

  @NotEmpty(message = "Missing %s")
  public String content;

  PostMessageCommand asCommand() {
    return new PostMessageCommand(userId, content);
  }
}
