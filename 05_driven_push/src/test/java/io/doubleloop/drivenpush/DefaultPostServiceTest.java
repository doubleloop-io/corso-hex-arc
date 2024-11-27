package io.doubleloop.drivenpush;

import io.doubleloop.drivenpush.domain.DefaultPostService;
import io.doubleloop.drivenpush.domain.PostMessageCommand;
import io.doubleloop.drivenpush.domain.PostNotifier;
import io.doubleloop.drivenpush.domain.User;
import io.doubleloop.drivenpush.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DefaultPostServiceTest {

  private final UserRepository userRepository = Mockito.mock(UserRepository.class);
  private final PostNotifier postNotifier = Mockito.mock(PostNotifier.class);

  private DefaultPostService postService;

  @BeforeEach
  void setUp() {
    postService = new DefaultPostService(userRepository, postNotifier);
  }

  @Test
  void userPostMessage() {
    final var user = new User("foo@bar.it", false);
    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

    final var command = new PostMessageCommand();
    command.setUserId(user.getId());
    command.setContent("test");

    postService.postMessage(command);

    verify(postNotifier).postMessage(command);
  }

  @Test
  void blockedUserPostMessage() {
    final var user = new User("foo@bar.it", true);
    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

    final var command = new PostMessageCommand();
    command.setUserId(user.getId());
    command.setContent("test");

    assertThatThrownBy(() -> postService.postMessage(command))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void unknownUserPostMessage() {
    when(userRepository.findById(any())).thenReturn(Optional.empty());

    final var command = new PostMessageCommand();
    command.setUserId("unknown");
    command.setContent("test");

    assertThatThrownBy(() -> postService.postMessage(command))
        .isInstanceOf(IllegalArgumentException.class);
  }
}