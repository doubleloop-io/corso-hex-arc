package io.doubleloop.baddesign.domain;

import java.time.LocalDate;
import java.util.List;

public interface AttendeeRegister {
  List<Attendee> attendeeListForEventOn(LocalDate date);
}
