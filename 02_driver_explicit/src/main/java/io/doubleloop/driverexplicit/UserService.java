package io.doubleloop.driverexplicit;

import org.springframework.stereotype.Service;

// TODO - 1: rename class in DefaultUserService
// TODO - 2: extract UserService interface from DefaultUserService
@Service
public class UserService {

  private final UserRepository userRepository;
  private final HashService hashService;

  public UserService(UserRepository userRepository, HashService hashService) {
    this.userRepository = userRepository;
    this.hashService = hashService;
  }

  public RegisterUserResult register(RegisterUserCommand command) {

    final var duplicated = userRepository.findByEmail(command.email);
    if (duplicated.isPresent())
      return RegisterUserResult.error(RegisterUserError.DUPLICATED_EMAIL);

    userRepository.save(new User(command.email, hashService.sha256(command.password)));

    return RegisterUserResult.success();
  }

  public GetUserResult getUserResult(GetUserQuery query) {
    final var user = userRepository.findByEmail(query.email);
    return user
        .map(GetUserResult::success)
        .orElseGet(GetUserResult::notFound);
  }
}
