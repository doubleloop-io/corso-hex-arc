package io.doubleloop.driverreactive;

import org.springframework.stereotype.Component;

import javax.json.bind.Jsonb;

@Component
public class WorkRecordConsumer {

  public static final String queueName = "work_hours";

  private final Jsonb jsonb;
  private final WorkRecordService service;

  public WorkRecordConsumer(Jsonb jsonb, WorkRecordService service) {
    this.jsonb = jsonb;
    this.service = service;
  }

  public void receiveMessage(String message) {
    final var request = jsonb.fromJson(message, WorkRecordRequest.class);
    if (request.action == null)
      throw new IllegalArgumentException("Missing action");

    final var action = WorkRecordAction.valueOf(request.action);
    switch (action) {
      case ADD -> service.onAddWorkRecord(request.asAddCommand());
      case REMOVE -> service.onRemoveWorkRecord(request.asRemoveCommand());
      default -> throw new IllegalStateException("Unexpected value: " + action);
    }
  }
}
