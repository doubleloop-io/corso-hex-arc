package io.doubleloop.problems.adapter;

import io.doubleloop.problems.domain.Greetings;
import io.doubleloop.problems.domain.GreetingsNotification;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SmtpGreetingsNotification implements GreetingsNotification {
  private final String smtpHost;
  private final int smtpPort;

  public SmtpGreetingsNotification(
      @Value("${app.smtp.host}") String smtpHost,
      @Value("${app.smtp.port}") int smtpPort) {
    this.smtpHost = smtpHost;
    this.smtpPort = smtpPort;
  }

  @Override
  public void sendMessage(Greetings greetings) throws MessagingException {
    final var session = getSession();
    final var msg = createMessage(greetings, session);
    Transport.send(msg);
  }

  private Session getSession() {
    final var props = new Properties();
    props.put("mail.smtp.host", this.smtpHost);
    props.put("mail.smtp.port", String.valueOf(this.smtpPort));
    return Session.getInstance(props, null);
  }

  private static MimeMessage createMessage(Greetings greetings, Session session) throws MessagingException {
    final var msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress("sender@here.com"));
    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(greetings.getEmail()));
    msg.setSubject("Happy Birthday!");
    msg.setText("Happy Birthday, dear %s".formatted(greetings.getName()));
    return msg;
  }
}
