package io.doubleloop.drivenpush;

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
  public String toString() {
    return "PostMessageCommand{" +
        "userId='" + userId + '\'' +
        ", content='" + content + '\'' +
        '}';
  }
}
