package io.doubleloop.driverimplicit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class UserControllerNoSpringTest {

  @Disabled("Implement TODO 1 and 3")
  @Test
  void registerValidUser() throws Exception {
    final var controller = new UserController(new UserService());
    final var request = new RegisterUserRequest();
    request.email = "foo@bar.it";
    request.password = "123456";

    final var response = controller.registerUser(request);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Disabled("Implement TODO 2 and 3")
  @Test
  void registerValidBusinessUser() throws Exception {
    final var controller = new UserController(new UserService());
    final var request = new RegisterUserRequest();
    request.email = "foo@bar.it";
    request.password = "123456";
    request.isBusiness = true;
    request.PIVA = "0123";

    final var response = controller.registerUser(request);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Disabled("Implement TODO 1 and 3")
  @Test
  void registerUserEmptyEmail() throws Exception {
    final var controller = new UserController(new UserService());
    final var request = new RegisterUserRequest();
    request.email = "";
    request.password = "123456";

    final var response = controller.registerUser(request);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

}
