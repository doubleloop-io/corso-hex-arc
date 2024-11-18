package io.doubleloop.baddesign.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class WeeklyEventTask {

  private final WeeklyEventService service;
  private final int attendeesThreshold;

  public WeeklyEventTask(WeeklyEventService service, @Value("${weeklyEventTask.attendeesThreshold}") int attendeesThreshold) {
    this.service = service;
    this.attendeesThreshold = attendeesThreshold;
  }

  @Scheduled(cron = "0 0 9 * * MON", zone = "UTC")
  public void executeTask() throws Exception {
    service.announceNextMeetupEvent(LocalDate.now(), attendeesThreshold);
  }
}
