package io.doubleloop.driverreactive.adapter;

import io.doubleloop.driverreactive.domain.AddWorkRecordCommand;
import io.doubleloop.driverreactive.domain.Hours;
import io.doubleloop.driverreactive.domain.RemoveWorkRecordCommand;
import io.doubleloop.driverreactive.domain.UserId;

import java.time.LocalDate;
import java.util.UUID;

public class WorkRecordRequest {
  public String action;
  public String userId;
  public int hours;
  public String date;

  @Override
  public String toString() {
    return "WorkRecordRequest{" +
        "action='" + action + '\'' +
        ", userId='" + userId + '\'' +
        ", hours=" + hours +
        ", date='" + date + '\'' +
        '}';
  }

  public AddWorkRecordCommand asAddCommand() {
    return new AddWorkRecordCommand(new UserId(UUID.fromString(userId)), new Hours(hours), LocalDate.parse(date));
  }

  public RemoveWorkRecordCommand asRemoveCommand() {
    return new RemoveWorkRecordCommand(new UserId(UUID.fromString(userId)), LocalDate.parse(date));
  }
}
