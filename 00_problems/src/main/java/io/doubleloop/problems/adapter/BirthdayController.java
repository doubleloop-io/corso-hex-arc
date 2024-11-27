package io.doubleloop.problems.adapter;

import io.doubleloop.problems.domain.BirthdayService;
import jakarta.mail.MessagingException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/birthday")
public class BirthdayController {

  private final BirthdayService service;

  public BirthdayController(BirthdayService service) {
    this.service = service;
  }

  @PostMapping("/sendGreetings")
  public ResponseEntity<Void> send(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws MessagingException, IOException {

    service.sendGreetings(getValueOrDefault(date));

    return ResponseEntity.ok().build();
  }

  private static LocalDate getValueOrDefault(LocalDate dateParam) {
    return dateParam != null ? dateParam : LocalDate.now();
  }
}
