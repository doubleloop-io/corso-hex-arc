package io.doubleloop.driverimplicit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
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
