package io.doubleloop.problems;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class BirthdayService {

  private final EmployeeRepository employeeRepository;
  private final GreetingsNotification greetingsNotification;

  public BirthdayService(EmployeeRepository employeeRepository,
                         GreetingsNotification greetingsNotification) {
    this.employeeRepository = employeeRepository;
    this.greetingsNotification = greetingsNotification;
  }

  public void sendGreetings(LocalDate today) throws IOException, MessagingException {
    final var employees = employeeRepository.readEmployees();

    final var greetingsList = employees.stream()
        .filter(x -> x.isBirthday(today))
        .map(Greetings::from)
        .toList();

    for (var greetings : greetingsList) {
      greetingsNotification.sendMessage(greetings);
    }
  }
}
