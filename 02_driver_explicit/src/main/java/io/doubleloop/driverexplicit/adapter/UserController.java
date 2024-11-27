package io.doubleloop.driverexplicit.adapter;

import io.doubleloop.driverexplicit.domain.GetUserQuery;
import io.doubleloop.driverexplicit.domain.GetUserResult;
import io.doubleloop.driverexplicit.domain.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    final var result = userService.register(request.asUser());

    if (result.isSuccess())
      return ResponseEntity.ok(request.email);
    if (result.isError())
      return ResponseEntity.unprocessableEntity().body(result.getError().toString());

    return ResponseEntity.internalServerError().build();
  }

  @GetMapping("/{userId}")
  public ResponseEntity<GetUserResult> getUser(@PathVariable String userId) {
    final var userResult = userService.getUserResult(new GetUserQuery(userId));
    return ResponseEntity.ok(userResult);
  }

}
