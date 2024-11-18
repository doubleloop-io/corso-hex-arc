package io.doubleloop.baddesign.adapter;

import io.doubleloop.baddesign.domain.EventsSchedule;
import io.doubleloop.baddesign.domain.ScheduledEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class GoogleCalendarEventsSchedule implements EventsSchedule {

  @Override
  public Optional<ScheduledEvent> nextWeeklyEvent(LocalDate today) {
    // NOTE: This is a fake implementation, it should be replaced with a real one
    return Optional.of(new ScheduledEvent(LocalDate.of(2024, 11, 26),
        "Hexagonal Architecture: Una Nuova Dimensione nella Progettazione Software",
        "Matteo Baglini"));
  }
}
