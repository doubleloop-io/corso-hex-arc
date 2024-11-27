package io.doubleloop.baddesign.domain;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WeeklyEventService {
  private final EventsSchedule eventsSchedule;
  private final AttendeeRegister attendeeRegister;
  private final NotificationChannel notificationChannel;

  public WeeklyEventService(
      EventsSchedule eventsSchedule,
      AttendeeRegister attendeeRegister,
      NotificationChannel notificationChannel
  ) {
    this.eventsSchedule = eventsSchedule;
    this.attendeeRegister = attendeeRegister;
    this.notificationChannel = notificationChannel;
  }

  public void announceNextMeetupEvent(LocalDate today, int attendeesThreshold) {
    final var foundEvent = eventsSchedule.nextWeeklyEvent(today);
    if (foundEvent.isEmpty()) {
      notificationChannel.noScheduledEvents();
      return;
    }

    final var scheduledEvent = foundEvent.get();
    final var attendees = attendeeRegister.attendeeListForEventOn(scheduledEvent.getDate());
    if (attendees.size() < attendeesThreshold) {
      notificationChannel.noEnoughAttendees(attendees.size(), attendeesThreshold);
      return;
    }

    notificationChannel.nextEvent(scheduledEvent, attendees.size());
  }

}
