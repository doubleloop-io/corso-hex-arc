package io.doubleloop.driverimplicit;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/")
  public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterUserRequest request) {
    final var result = !request.isBusiness
        // TODO - 1: implement the asUser method
        ? userService.register(request.asUser())
        // TODO - 2: implement the asBusinessUser method
        : userService.register(request.asBusinessUser());

    // TODO - 3: returns a 200 OK response with the email in the body when result is success

    // TODO - 4: returns a 422 UNPROCESSABLE_ENTITY response with the error in the body when result is error

    return ResponseEntity.internalServerError().build();
  }

}
