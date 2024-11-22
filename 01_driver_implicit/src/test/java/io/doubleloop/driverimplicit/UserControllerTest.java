package io.doubleloop.driverimplicit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void registerValidUser() throws Exception {
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
  void registerValidBusinessUser() throws Exception {
    final var json = """
        { 
          "email": "foo@bar.it",
          "password": "123456",
          "isBusiness": true,
          "PIVA": "0123"
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
  void registerBusinessUserEmptyPIVA() throws Exception {
    final var json = """
        { 
          "email": "foo@bar.it",
          "password": "123456",
          "isBusiness": true,
          "PIVA": ""
        }
        """;

    mockMvc.perform(post("/api/users/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void registerDuplicatedEmail() throws Exception {
    final var json = """
        { 
          "email": "duplicated@bar.it",
          "password": "123456",
          "isBusiness": true,
          "PIVA": "0123"
        }
        """;

    mockMvc.perform(post("/api/users/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity())
        .andExpect(content().string("DUPLICATED_EMAIL"));
  }

  @Test
  void registerUnregisteredPIVA() throws Exception {
    final var json = """
        { 
          "email": "foo@bar.it",
          "password": "123456",
          "isBusiness": true,
          "PIVA": "0000"
        }
        """;

    mockMvc.perform(post("/api/users/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity())
        .andExpect(content().string("UNREGISTERED_PIVA"));
  }
}
