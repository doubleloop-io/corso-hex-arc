package io.doubleloop.baddesign.domain;

import java.time.LocalDate;
import java.util.Optional;

public interface EventsSchedule {
  Optional<ScheduledEvent> nextWeeklyEvent(LocalDate today);
}
