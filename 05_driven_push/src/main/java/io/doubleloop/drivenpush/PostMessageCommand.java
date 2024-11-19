package io.doubleloop.drivenpush;

import java.util.Objects;

public class PostMessageCommand {

  private String userId;
  private String content;

  public PostMessageCommand() {
  }

  public PostMessageCommand(String userId, String content) {
    this.userId = userId;
    this.content = content;
  }

  public String getUserId() {
    return userId;
  }

  public String getContent() {
    return content;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    PostMessageCommand that = (PostMessageCommand) o;
    return Objects.equals(userId, that.userId) && Objects.equals(content, that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, content);
  }

  @Override
  public String toString() {
    return "PostMessageCommand{" +
        "userId='" + userId + '\'' +
        ", content='" + content + '\'' +
        '}';
  }
}
