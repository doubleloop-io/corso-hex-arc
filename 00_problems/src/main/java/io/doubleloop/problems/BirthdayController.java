package io.doubleloop.problems;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
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
  private final String filePath;
  private final String smtpHost;
  private final int smtpPort;

  public BirthdayController(BirthdayService service,
                            @Value("${app.employee.file}") String filePath,
                            @Value("${app.smtp.host}") String smtpHost,
                            @Value("${app.smtp.port}") int smtpPort) {
    this.service = service;
    this.filePath = filePath;
    this.smtpHost = smtpHost;
    this.smtpPort = smtpPort;
  }

  @PostMapping("/sendGreetings")
  public ResponseEntity<Void> send(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws MessagingException, IOException {

    service.sendGreetings(
        filePath,
        getValueOrDefault(date),
        smtpHost,
        smtpPort
    );

    return ResponseEntity.ok().build();
  }

  private static LocalDate getValueOrDefault(LocalDate dateParam) {
    return dateParam != null ? dateParam : LocalDate.now();
  }
}
