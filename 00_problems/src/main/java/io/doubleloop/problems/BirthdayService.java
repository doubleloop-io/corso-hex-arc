package io.doubleloop.problems;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Properties;

@Component
public class BirthdayService {

  private final String filePath;
  private final String smtpHost;
  private final int smtpPort;

  public BirthdayService(@Value("${app.employee.file}") String filePath,
                         @Value("${app.smtp.host}") String smtpHost,
                         @Value("${app.smtp.port}") int smtpPort) {
    this.filePath = filePath;
    this.smtpHost = smtpHost;
    this.smtpPort = smtpPort;
  }

  public void sendGreetings(LocalDate today) throws IOException, MessagingException {

    final var employees = Files.readAllLines(Paths.get(filePath))
        .stream()
        .skip(1) // skip header
        .map(this::parseLine)
        .toList();

    for (var employee : employees) {
      if (employee.isBirthday(today)) {
        String recipient = employee.getEmail();
        String body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.getFirstName());
        String subject = "Happy Birthday!";
        sendMessage("sender@here.com", subject, body, recipient);
      }
    }
  }

  private Employee parseLine(String str) {
    final var employeeData = str.split(", ");
    return new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
  }

  private void sendMessage(String sender, String subject, String body, String recipient) throws MessagingException {
    final var props = new Properties();
    props.put("mail.smtp.host", smtpHost);
    props.put("mail.smtp.port", String.valueOf(smtpPort));
    final var session = Session.getInstance(props, null);

    final var msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(sender));
    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    msg.setSubject(subject);
    msg.setText(body);

    Transport.send(msg);
  }
}
