package io.doubleloop.driverimplicit;

import io.doubleloop.driverimplicit.adapter.RegisterUserRequest;
import io.doubleloop.driverimplicit.adapter.UserController;
import io.doubleloop.driverimplicit.domain.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class UserControllerNoSpringTest {

  @Test
  void registerValidUser() {
    final var controller = new UserController(new UserService());
    final var request = new RegisterUserRequest();
    request.email = "foo@bar.it";
    request.password = "123456";

    final var response = controller.registerUser(request);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void registerValidBusinessUser() {
    final var controller = new UserController(new UserService());
    final var request = new RegisterUserRequest();
    request.email = "foo@bar.it";
    request.password = "123456";
    request.isBusiness = true;
    request.PIVA = "0123";

    final var response = controller.registerUser(request);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Disabled("This test can not be green.")
  @Test
  void registerUserEmptyEmail() {
    final var controller = new UserController(new UserService());
    final var request = new RegisterUserRequest();
    request.email = "";
    request.password = "123456";

    final var response = controller.registerUser(request);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

}
