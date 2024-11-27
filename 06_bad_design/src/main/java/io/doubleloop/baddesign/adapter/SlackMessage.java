package io.doubleloop.baddesign.adapter;

// NOTE: read here for more info https://api.slack.com/methods/chat.postMessage#text_usage
public class SlackMessage {
  private String plainText;
  private String markdown;

  public SlackMessage(String plainText, String markdown) {
    this.plainText = plainText;
    this.markdown = markdown;
  }

  static SlackMessage of(String plainText) {
    return new SlackMessage(plainText, plainText);
  }

  static SlackMessage of(String plainText, String markdown) {
    return new SlackMessage(plainText, markdown);
  }

  public String getPlainText() {
    return plainText;
  }

  public String getMarkdown() {
    return markdown;
  }
}
