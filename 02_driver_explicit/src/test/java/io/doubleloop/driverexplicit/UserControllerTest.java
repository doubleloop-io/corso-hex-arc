package io.doubleloop.driverexplicit;

import io.doubleloop.driverexplicit.domain.GetUserResult;
import io.doubleloop.driverexplicit.domain.RegisterUserError;
import io.doubleloop.driverexplicit.domain.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  void registerValidUser() throws Exception {
    when(userService.register(any())).thenReturn(GetUserResult.RegisterUserResult.success());

    final var json = """
        {
          "email": "foo@bar.it",
          "password": "123456"
        }
        """;

    mockMvc.perform(post("/api/users/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("foo@bar.it")));
  }

  @Test
  void registerUserEmptyEmail() throws Exception {
    final var json = """
        {
          "email": "",
          "password": "123456"
        }
        """;

    mockMvc.perform(post("/api/users/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void registerUserEmptyPassword() throws Exception {
    final var json = """
        {
          "email": "foo@bar.it",
          "password": ""
        }
        """;

    mockMvc.perform(post("/api/users/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void registerDuplicatedEmail() throws Exception {
    when(userService.register(any())).thenReturn(GetUserResult.RegisterUserResult.error(RegisterUserError.DUPLICATED_EMAIL));

    final var json = """
        {
          "email": "duplicated@bar.it",
          "password": "123456"
        }
        """;

    mockMvc.perform(post("/api/users/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity())
        .andExpect(content().string("DUPLICATED_EMAIL"));
  }
}
