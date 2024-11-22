package io.doubleloop.drivenpull;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DailyBalanceControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DailyBalanceService dailyBalanceService;

  @Test
  void dailyUserBalance() throws Exception {
    final var result = new BalanceResult("123", LocalDate.of(2024, 11, 15), 100.0);
    when(dailyBalanceService.balanceOn(Mockito.any())).thenReturn(result);

    mockMvc.perform(get("/api/balance/daily/123"))
        .andExpect(status().isOk())
        .andExpect(content().string("""
               {"userId":"123","date":"2024-11-15","balance":100.0}"""));
  }
}