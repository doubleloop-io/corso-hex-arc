package io.doubleloop.driverexplicit.domain;

import io.doubleloop.driverexplicit.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

  private final UserRepository userRepository;
  private final HashService hashService;

  public DefaultUserService(UserRepository userRepository, HashService hashService) {
    this.userRepository = userRepository;
    this.hashService = hashService;
  }

  @Override
  public GetUserResult.RegisterUserResult register(RegisterUserCommand command) {

    final var duplicated = userRepository.findByEmail(command.getEmail());
    if (duplicated.isPresent())
      return GetUserResult.RegisterUserResult.error(RegisterUserError.DUPLICATED_EMAIL);

    userRepository.save(new GetUserResult.User(command.getEmail(), hashService.sha256(command.getPassword())));

    return GetUserResult.RegisterUserResult.success();
  }

  @Override
  public GetUserResult getUserResult(GetUserQuery query) {
    final var found = userRepository.findByEmail(query.getEmail());
    if (found.isEmpty())
      throw new IllegalArgumentException("User not found");
    return new GetUserResult(found.get());
  }
}
