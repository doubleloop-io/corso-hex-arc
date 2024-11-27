package io.doubleloop.driverexplicit.domain;

public interface UserService {
  GetUserResult.RegisterUserResult register(RegisterUserCommand command);

  GetUserResult getUserResult(GetUserQuery query);
}
