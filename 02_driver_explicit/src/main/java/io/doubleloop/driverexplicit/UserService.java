package io.doubleloop.driverexplicit;

import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final HashService hashService;

  public UserService(UserRepository userRepository, HashService hashService) {
    this.userRepository = userRepository;
    this.hashService = hashService;
  }

  public RegisterUserResult register(RegisterUserCommand command) {

    final var duplicated = userRepository.findByEmail(command.getEmail());
    if (duplicated.isPresent())
      return RegisterUserResult.error(RegisterUserError.DUPLICATED_EMAIL);

    userRepository.save(new User(command.getEmail(), hashService.sha256(command.getPassword())));

    return RegisterUserResult.success();
  }

  public GetUserResult getUserResult(GetUserQuery query) {
    final var found = userRepository.findByEmail(query.getEmail());
    if (found.isEmpty())
      throw new IllegalArgumentException("User not found");
    return new GetUserResult(found.get());
  }
}
