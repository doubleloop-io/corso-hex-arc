package io.doubleloop.drivenpush.domain;

import org.springframework.stereotype.Service;

@Service
public class DefaultPostService implements PostService {

  private final UserRepository userRepository;
  private final PostNotifier postNotifier;

  public DefaultPostService(UserRepository userRepository, PostNotifier postNotifier) {
    this.userRepository = userRepository;
    this.postNotifier = postNotifier;
  }

  @Override
  public void postMessage(PostMessageCommand command) {
    final var found = userRepository.findById(command.getUserId());
    if (found.isEmpty()) {
      throw new IllegalArgumentException("User not found");
    }

    final var user = found.get();
    if (user.isBlocked()) {
      throw new IllegalArgumentException("User is blocked");
    }

    postNotifier.postMessage(command);
  }
}
