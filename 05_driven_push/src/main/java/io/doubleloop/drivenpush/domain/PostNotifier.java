package io.doubleloop.drivenpush.domain;

public interface PostNotifier {
  void postMessage(PostMessageCommand command);
}
