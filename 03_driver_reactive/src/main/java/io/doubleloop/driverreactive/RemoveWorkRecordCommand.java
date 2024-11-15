package io.doubleloop.driverreactive;

import java.time.LocalDate;

public class RemoveWorkRecordCommand {
  private final UserId userId;
  private final LocalDate date;

  public RemoveWorkRecordCommand(UserId userId, LocalDate date) {
    this.userId = userId;
    this.date = date;
  }

  @Override
  public String toString() {
    return "RemoveWorkRecordCommand{" +
        "userId=" + userId +
        ", date=" + date +
        '}';
  }
}
