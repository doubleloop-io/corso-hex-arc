package io.doubleloop.baddesign.adapter;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import io.doubleloop.baddesign.domain.NotificationChannel;
import io.doubleloop.baddesign.domain.SlackMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.slack.api.model.block.Blocks.asBlocks;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;

// DOCS:
// - https://slack.dev/node-slack-sdk/web-api
// - https://api.slack.com/tools/block-kit-builder

@Component
public class SlackNotificationChannel implements NotificationChannel {

  private Slack slack;
  private final String token;

  public SlackNotificationChannel(Slack slack, @Value("${slack.token}") String token) {
    this.slack = slack;
    this.token = token;
  }

  @Override
  public void sendMessage(SlackMessage message) throws IOException, SlackApiException {
    // NOTE: This is a fake implementation, it should be replaced with a real one

    slack.methods(token)
        .chatPostMessage(req -> req
            .channel("#events")
            .blocks(asBlocks(
                section(section -> section.text(markdownText(message.getMarkdown())))
            ))
            .text(message.getPlainText())
        );
  }
}
