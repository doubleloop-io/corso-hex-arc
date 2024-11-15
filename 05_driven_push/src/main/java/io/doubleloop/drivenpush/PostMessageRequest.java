package io.doubleloop.drivenpush;

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
