package io.doubleloop.driverreactive.domain;

import java.time.LocalDate;

public class AddWorkRecordCommand {
  private final UserId userId;
  private final Hours hours;
  private final LocalDate date;

  public AddWorkRecordCommand(UserId userId, Hours hours, LocalDate date) {
    this.userId = userId;
    this.hours = hours;
    this.date = date;
  }

  @Override
  public String toString() {
    return "AddWorkRecordCommand{" +
        "userId=" + userId +
        ", hours=" + hours +
        ", date=" + date +
        '}';
  }
}
