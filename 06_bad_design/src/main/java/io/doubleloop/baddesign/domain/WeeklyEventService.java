package io.doubleloop.baddesign.domain;

import com.slack.api.methods.SlackApiException;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

  public void announceNextMeetupEvent(LocalDate today, int attendeesThreshold) throws IOException, SlackApiException {
    final var foundEvent = eventsSchedule.nextWeeklyEvent(today);
    if (foundEvent.isEmpty()) {
      notificationChannel.sendMessage(SlackAnnouncements.noScheduledEvents());
      return;
    }

    final var scheduledEvent = foundEvent.get();
    final var attendees = attendeeRegister.attendeeListForEventOn(scheduledEvent.getDate());
    if (attendees.size() < attendeesThreshold) {
      notificationChannel.sendMessage(SlackAnnouncements.noEnoughAttendees(attendees.size(), attendeesThreshold));
      return;
    }

    notificationChannel.sendMessage(SlackAnnouncements.nextEvent(scheduledEvent, attendees.size()));
  }

}
