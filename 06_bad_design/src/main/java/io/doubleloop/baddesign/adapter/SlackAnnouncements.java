package io.doubleloop.baddesign.adapter;

import io.doubleloop.baddesign.domain.ScheduledEvent;

public class SlackAnnouncements {

  private SlackAnnouncements() {
  }

  public static SlackMessage noScheduledEvents() {
    return SlackMessage.of("No event scheduled for this week.");
  }

  public static SlackMessage noEnoughAttendees(int actual, int required) {
    return SlackMessage.of("Not enough attendees for this week (%s of %s required)."
        .formatted(actual, required));
  }

  public static SlackMessage nextEvent(ScheduledEvent scheduledEvent, int attendeesCount) {
    return SlackMessage.of(
        "Next week event is %s by @%s with %s attendees."
            .formatted(scheduledEvent.getTitle(), scheduledEvent.getSpeaker(), attendeesCount),
        "Next week event is **%s** by <@%s> with %s attendees."
            .formatted(scheduledEvent.getTitle(), scheduledEvent.getSpeaker(), attendeesCount)
    );
  }
}
