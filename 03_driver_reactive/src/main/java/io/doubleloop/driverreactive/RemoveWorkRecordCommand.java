package io.doubleloop.driverreactive;

import java.time.LocalDate;

public class RemoveWorkRecordCommand {
  private UserId userId;
  private LocalDate date;

  public RemoveWorkRecordCommand(UserId userId, LocalDate date) {
    this.userId = userId;
    this.date = date;
  }

  public UserId getUserId() {
    return userId;
  }

  public LocalDate getDate() {
    return date;
  }

  @Override
  public String toString() {
    return "RemoveWorkRecordCommand{" +
        "userId=" + userId +
        ", date=" + date +
        '}';
  }
}
