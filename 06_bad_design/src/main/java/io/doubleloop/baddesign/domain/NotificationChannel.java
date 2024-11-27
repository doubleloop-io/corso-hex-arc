package io.doubleloop.baddesign.domain;

public interface NotificationChannel {
  void noScheduledEvents();
  void noEnoughAttendees(int size, int attendeesThreshold);
  void nextEvent(ScheduledEvent scheduledEvent, int size);
}
