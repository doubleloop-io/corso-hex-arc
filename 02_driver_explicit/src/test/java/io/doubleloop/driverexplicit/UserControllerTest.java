package io.doubleloop.driverexplicit;

import io.doubleloop.driverexplicit.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  // TODO - 4: remove mongodb container field and annotations
  @Container
  @ServiceConnection
  private static MongoDBContainer container = new MongoDBContainer("mongo:latest");

  // TODO - 5: remove Autowired field
  @Autowired
  private UserRepository userRepository;

  // TODO - 6: define a UserService interface mock field with @MockBean annotation

  // TODO - 7: completely remove setUp method and the necessity to clean up the database
  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
  }

  // TODO - 8: add a when expression the return different UserService's result based on test case

  @Test
  void registerValidUser() throws Exception {
    final var json = "{" +
        "\"email\": \"foo@bar.it\"," +
        "\"password\": \"123456\"" +
        "}";

    mockMvc.perform(post("/api/users/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("foo@bar.it")));
  }

  @Test
  void registerUserEmptyEmail() throws Exception {
    final var json = "{" +
        "\"email\": \"\"," +
        "\"password\": \"123456\"" +
        "}";

    mockMvc.perform(post("/api/users/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void registerUserEmptyPassword() throws Exception {
    final var json = "{" +
        "\"email\": \"foo@bar.it\"," +
        "\"password\": \"\"" +
        "}";

    mockMvc.perform(post("/api/users/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void registerDuplicatedEmail() throws Exception {
    userRepository.save(new User("duplicated@bar.it", "123456"));

    final var json = "{" +
        "\"email\": \"duplicated@bar.it\"," +
        "\"password\": \"123456\"" +
        "}";

    mockMvc.perform(post("/api/users/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity())
        .andExpect(content().string("DUPLICATED_EMAIL"));
  }
}
