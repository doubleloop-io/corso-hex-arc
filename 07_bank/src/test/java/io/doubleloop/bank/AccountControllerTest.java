package io.doubleloop.bank;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void openAccount() throws Exception {
    String json = """
        {
            "email": "foo.bar@mail.com",
            "firstName": "foo",
            "lastName": "bar",
            "CF": "FOOBAR",
            "initialBalance": "0"
        }
        """;

    mockMvc.perform(post("/api/accounts/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void openAccountMissingEmail() throws Exception {
    String json = """
        {
            "firstName": "foo",
            "lastName": "bar",
            "CF": "FOOBAR",
            "initialBalance": "0"
        }
        """;

    mockMvc.perform(post("/api/accounts/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void openAccountMissingCF() throws Exception {
    String json = """
        {
            "email": "foo.bar@mail.com",
            "firstName": "foo",
            "lastName": "bar",
            "initialBalance": "0"
        }
        """;

    mockMvc.perform(post("/api/accounts/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void openAccountInvalidIdentity() throws Exception {
    String json = """
        {
            "email": "foo.bar@mail.com",
            "firstName": "foo",
            "lastName": "bar",
            "CF": "INVALID",
            "initialBalance": "0"
        }
        """;

    mockMvc.perform(post("/api/accounts/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  void openAccountTooYoung() throws Exception {
    String json = """
        {
            "email": "foo.bar@mail.com",
            "firstName": "foo",
            "lastName": "bar",
            "CF": "INVALID",
            "initialBalance": "0"
        }
        """;

    mockMvc.perform(post("/api/accounts/")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  void depositAmount() throws Exception {
    String json = """
        {
            "type": "deposit",
            "amount": "100"
        }
        """;

    mockMvc.perform(post("/api/accounts/123")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void depositAmountForUnknownAccount() throws Exception {
    String json = """
        {
            "type": "deposit",
            "amount": "100"
        }
        """;

    mockMvc.perform(post("/api/accounts/555")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void withdrawAmount() throws Exception {
    String json = """
        {
            "type": "withdraw",
            "amount": "100"
        }
        """;

    mockMvc.perform(post("/api/accounts/123")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void withdrawAmountForUnknownAccount() throws Exception {
    String json = """
        {
            "type": "withdraw",
            "amount": "100"
        }
        """;

    mockMvc.perform(post("/api/accounts/555")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void withdrawAmountOverBalance() throws Exception {
    String json = """
        {
            "type": "withdraw",
            "amount": "150"
        }
        """;

    mockMvc.perform(post("/api/accounts/123")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  void transfer() throws Exception {
    String json = """
        {
            "sourceAccountId": "123",
            "destinationAccountId": "456",
            "amount": "100"
        }
        """;

    mockMvc.perform(post("/api/accounts/transfer")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void closeAccount() throws Exception {
    mockMvc.perform(delete("/api/accounts/123"))
        .andExpect(status().isOk());
  }

  @Test
  void closeUnknownAccount() throws Exception {
    mockMvc.perform(delete("/api/accounts/555"))
        .andExpect(status().isNotFound());
  }
}
