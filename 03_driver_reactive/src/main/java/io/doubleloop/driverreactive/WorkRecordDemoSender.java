package io.doubleloop.driverreactive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.json.bind.Jsonb;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class WorkRecordDemoSender implements CommandLineRunner {

  public static final String exchangeName = "work_hours";

  private static final Logger log = LoggerFactory.getLogger(WorkRecordDemoSender.class);

  private static final String UserId = "d324c79d-a477-4171-945b-dcde2418eefc";

  private final RabbitTemplate rabbitTemplate;
  private final Jsonb jsonb;

  public WorkRecordDemoSender(RabbitTemplate rabbitTemplate, Jsonb jsonb) {
    this.rabbitTemplate = rabbitTemplate;
    this.jsonb = jsonb;
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Sending messages...");

    final var today = LocalDate.now();
    final var yesterday = today.minus(1, ChronoUnit.DAYS);

    rabbitTemplate.convertAndSend(exchangeName, "", addWorkHoursOn(yesterday, 10));
    rabbitTemplate.convertAndSend(exchangeName, "", addWorkHoursOn(today, 6));
    rabbitTemplate.convertAndSend(exchangeName, "", removeWorkHoursOn(today));
  }

  private String addWorkHoursOn(LocalDate date, int hours) {
    final var request = new WorkRecordRequest();
    request.action = WorkRecordAction.ADD.name();
    request.userId = UserId;
    request.hours = hours;
    request.date = date.toString();

    return jsonb.toJson(request);
  }

  private String removeWorkHoursOn(LocalDate date) {
    final var request = new WorkRecordRequest();
    request.action = WorkRecordAction.REMOVE.name();
    request.userId = UserId;
    request.date = date.toString();

    return jsonb.toJson(request);
  }
}
