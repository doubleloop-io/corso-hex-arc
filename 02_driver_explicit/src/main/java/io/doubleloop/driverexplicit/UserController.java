package io.doubleloop.driverexplicit;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  // TODO - 3: be sure to depends from UserService and not DefaultUserService
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/")
  public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterUserRequest request) {
    final var result = userService.register(request.asUser());

    if (result.isSuccess())
      return ResponseEntity.ok(request.email);
    if (result.isError())
      return ResponseEntity.unprocessableEntity().body(result.getError().toString());

    return ResponseEntity.internalServerError().build();
  }

}
