package io.doubleloop.baddesign.domain;

import com.slack.api.methods.SlackApiException;

import java.io.IOException;

public interface NotificationChannel {
  void sendMessage(SlackMessage message) throws IOException, SlackApiException;
}
