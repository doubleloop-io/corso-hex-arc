package io.doubleloop.driverexplicit.domain;

import io.doubleloop.driverexplicit.SpringUserStorage;
import org.springframework.stereotype.Service;

// TODO - 1: remove class in DefaultUserService
// TODO - 2: extract UserService interface from DefaultUserService
@Service
public class UserService {

  private final SpringUserStorage userStorage;
  private final HashService hashService;

  public UserService(SpringUserStorage userStorage, HashService hashService) {
    this.userStorage = userStorage;
    this.hashService = hashService;
  }

  public RegisterUserResult register(RegisterUserCommand request) {

    final var duplicated = userStorage.findByEmail(request.email);
    if (duplicated.isPresent())
      return RegisterUserResult.error(RegisterUserError.DUPLICATED_EMAIL);

    userStorage.save(new User(request.email, hashService.sha256(request.password)));

    return RegisterUserResult.success();
  }
}
