package io.doubleloop.problems;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BirthdayServiceTest {

  private String filePath;
  private String smtpHost = "localhost";
  private int smtpPort = 3025;
  private GreenMail mailServer;

  @BeforeEach
  void setUp() {
    filePath = getClass()
        .getClassLoader()
        .getResource("employee_data.txt")
        .getPath();

    mailServer = new GreenMail(new ServerSetup(smtpPort, null, "smtp"));
    mailServer.start();
  }

  @AfterEach
  void tearDown() {
    mailServer.stop();
  }

  @Test
  void testSendMessage() throws Exception {
    final var repository = new EmployeeRepository(filePath);
    final var notification = new GreetingsNotification(smtpHost, smtpPort);
    final var emailService = new BirthdayService(repository, notification);

    emailService.sendGreetings(LocalDate.of(2024, 10, 8));

    final var receivedMessages = mailServer.getReceivedMessages();
    assertEquals(1, receivedMessages.length);

    final var receivedMessage = receivedMessages[0];
    assertEquals("Happy Birthday!", receivedMessage.getSubject());
    assertEquals("Happy Birthday, dear John", receivedMessage.getContent().toString());
    assertEquals("sender@here.com", receivedMessage.getFrom()[0].toString());
    assertEquals("john.doe@foobar.com", receivedMessage.getRecipients(Message.RecipientType.TO)[0].toString());
  }
}