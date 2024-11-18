package io.doubleloop.driverimplicit;

import org.springframework.stereotype.Service;

// NOTE: This is a fake implementation, it should be replaced with a real one
@Service
public class UserService {

  public RegisterUserResult register(RegisterUserCommand request) {
    if (request.getEmail().contains("duplicated"))
      return RegisterUserResult.error(RegisterUserError.DUPLICATED_EMAIL);

    return RegisterUserResult.success();
  }

  public RegisterUserResult register(RegisterBusinessUserCommand request) {
    if (request.getEmail().contains("duplicated"))
      return RegisterUserResult.error(RegisterUserError.DUPLICATED_EMAIL);

    if (request.getPIVA().contains("0000"))
      return RegisterUserResult.error(RegisterUserError.UNREGISTERED_PIVA);

    return RegisterUserResult.success();
  }
}
