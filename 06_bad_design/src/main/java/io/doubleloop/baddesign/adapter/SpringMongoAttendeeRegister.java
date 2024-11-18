package io.doubleloop.baddesign.adapter;

import io.doubleloop.baddesign.domain.Attendee;
import io.doubleloop.baddesign.domain.AttendeeRegister;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SpringMongoAttendeeRegister implements AttendeeRegister {

  @Override
  public List<Attendee> attendeeListForEventOn(LocalDate date) {
    // NOTE: This is a fake implementation, it should be replaced with a real one
    return List.of(new Attendee("Matteo", "matteo@doubleloop.io"), new Attendee("Mario", "mario@rossi.it"));
  }
}
