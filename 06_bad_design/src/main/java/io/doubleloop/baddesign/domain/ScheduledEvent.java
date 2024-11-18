package io.doubleloop.baddesign.domain;

import java.time.LocalDate;

public class ScheduledEvent {

  private final LocalDate date;
  private final String title;
  private final String speaker;

  public ScheduledEvent(LocalDate date, String title, String speaker) {
    this.date = date;
    this.title = title;
    this.speaker = speaker;
  }

  public LocalDate getDate() {
    return date;
  }

  public String getTitle() {
    return title;
  }

  public String getSpeaker() {
    return speaker;
  }
}
