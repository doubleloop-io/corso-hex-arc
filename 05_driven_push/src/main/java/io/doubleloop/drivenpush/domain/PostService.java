package io.doubleloop.drivenpush.domain;

public interface PostService {
  void postMessage(PostMessageCommand command);
}
