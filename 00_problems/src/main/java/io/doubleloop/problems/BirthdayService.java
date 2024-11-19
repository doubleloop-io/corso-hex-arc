package io.doubleloop.problems;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    BufferedReader in = new BufferedReader(new FileReader(filePath));
    String str = "";
    str = in.readLine(); // skip header
    while ((str = in.readLine()) != null) {
      String[] employeeData = str.split(", ");
      Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
      if (employee.isBirthday(today)) {
        String recipient = employee.getEmail();
        String body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.getFirstName());
        String subject = "Happy Birthday!";
        sendMessage("sender@here.com", subject, body, recipient);
      }
    }
  }

  private void sendMessage(String sender, String subject, String body, String recipient) throws MessagingException {
    Properties props = new Properties();
    props.put("mail.smtp.host", smtpHost);
    props.put("mail.smtp.port", String.valueOf(smtpPort));
    Session session = Session.getInstance(props, null);

    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(sender));
    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    msg.setSubject(subject);
    msg.setText(body);

    Transport.send(msg);
  }
}
