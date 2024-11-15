package io.doubleloop.driverexplicit.domain;

import io.doubleloop.driverexplicit.SpringUserStorage;
import org.springframework.stereotype.Service;

// TODO - 1: rename class in DefaultUserService
// TODO - 2: extract UserService interface from DefaultUserService
@Service
public class UserService {

  private final SpringUserStorage userStorage;
  private final HashService hashService;

  public UserService(SpringUserStorage userStorage, HashService hashService) {
    this.userStorage = userStorage;
    this.hashService = hashService;
  }

  public RegisterUserResult register(RegisterUserCommand command) {

    final var duplicated = userStorage.findByEmail(command.email);
    if (duplicated.isPresent())
      return RegisterUserResult.error(RegisterUserError.DUPLICATED_EMAIL);

    userStorage.save(new User(command.email, hashService.sha256(command.password)));

    return RegisterUserResult.success();
  }

  public GetUserResult getUserResult(GetUserQuery query) {
    final var user = userStorage.findByEmail(query.email);
    return user
        .map(GetUserResult::success)
        .orElseGet(GetUserResult::notFound);
  }
}
