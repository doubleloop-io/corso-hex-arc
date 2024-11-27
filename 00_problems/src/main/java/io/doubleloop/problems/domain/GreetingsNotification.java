package io.doubleloop.problems.domain;

import jakarta.mail.MessagingException;

public interface GreetingsNotification {
  void sendMessage(Greetings greetings) throws MessagingException;
}
