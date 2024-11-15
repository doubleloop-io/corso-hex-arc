package io.doubleloop.driverreactive;

import java.time.LocalDate;

public class WorkRecord {
  private final UserId userId;
  private final Hours hours;
  private final LocalDate date;

  public WorkRecord(UserId userId, Hours hours, LocalDate date) {
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
    return "WorkRecord{" +
        "userId=" + userId +
        ", hours=" + hours +
        ", date=" + date +
        '}';
  }
}
