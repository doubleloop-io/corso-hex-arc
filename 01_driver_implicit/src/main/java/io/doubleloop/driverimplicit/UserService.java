package io.doubleloop.driverimplicit;

import org.springframework.stereotype.Service;

// NOTE: Just a demo/fake implementation
@Service
public class UserService {

  public RegisterUserResult register(RegisterUserCommand request) {
    if (request.email.contains("duplicated"))
      return RegisterUserResult.error(RegisterUserError.DUPLICATED_EMAIL);

    return RegisterUserResult.success();
  }

  public RegisterUserResult register(RegisterBusinessUserCommand request) {
    if (request.email.contains("duplicated"))
      return RegisterUserResult.error(RegisterUserError.DUPLICATED_EMAIL);

    if (request.PIVA.contains("0000"))
      return RegisterUserResult.error(RegisterUserError.UNREGISTERED_PIVA);

    return RegisterUserResult.success();
  }
}
