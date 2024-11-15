package io.doubleloop.driverreactive;

import java.time.LocalDate;

public class AddWorkRecordCommand {
  private UserId userId;
  private Hours hours;
  private LocalDate date;

  public AddWorkRecordCommand(UserId userId, Hours hours, LocalDate date) {
    this.userId = userId;
    this.hours = hours;
    this.date = date;
  }

  public UserId getUserId() {
    return userId;
  }

  public Hours getHours() {
    return hours;
  }

  public LocalDate getDate() {
    return date;
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
