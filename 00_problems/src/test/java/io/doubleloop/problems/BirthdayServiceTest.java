package io.doubleloop.problems;

import io.doubleloop.problems.domain.BirthdayService;
import io.doubleloop.problems.domain.Employee;
import io.doubleloop.problems.domain.EmployeeRepository;
import io.doubleloop.problems.domain.Greetings;
import io.doubleloop.problems.domain.GreetingsNotification;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BirthdayServiceTest {

  @Test
  void testSendMessage() throws Exception {
    final var repository = mock(EmployeeRepository.class);
    when(repository.readEmployees())
        .thenReturn(List.of(
            new Employee("John", "Doe", "1982/10/08", "john.doe@foobar.com"),
            new Employee("Mary", "Ann", "1975/03/11", "mary.ann@foobar.com")
        ));
    final var notification = mock(GreetingsNotification.class);

    final var emailService = new BirthdayService(repository, notification);

    emailService.sendGreetings(LocalDate.of(2024, 10, 8));

    verify(notification).sendMessage(new Greetings("John", "john.doe@foobar.com"));
  }
}