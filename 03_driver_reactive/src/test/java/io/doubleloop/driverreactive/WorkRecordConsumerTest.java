package io.doubleloop.driverreactive;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class WorkRecordConsumerTest {
  private WorkRecordService service;
  private Jsonb jsonb;
  private WorkRecordConsumer consumer;

  @BeforeEach
  void setUp() {
    service = mock(WorkRecordService.class);
    jsonb = JsonbBuilder.create();
    consumer = new WorkRecordConsumer(jsonb, service);
  }

  @AfterEach
  void after() {
    verifyNoMoreInteractions(service);
  }

  @Test
  void addWorkRecord() {
    String message = """
        {
            "action": "ADD",
            "userId": "ebd971c2-40d4-47c5-bee7-a8868b8d6f71",
            "hours": 6,
            "date": "2014-11-10"
        }
        """;

    consumer.receiveMessage(message);

    verify(service).onAddWorkRecord(any());
  }

  @Test
  void removeWorkRecord() {
    String message = """
        {
            "action": "REMOVE",
            "userId": "ebd971c2-40d4-47c5-bee7-a8868b8d6f71",
            "date": "2014-11-10"
        }
        """;

    consumer.receiveMessage(message);

    verify(service).onRemoveWorkRecord(any());
  }

  @Test
  void unknownAction() {
    String message = """
        {
            "action": "DUPLICATE",
            "userId": "ebd971c2-40d4-47c5-bee7-a8868b8d6f71",
            "date": "2014-11-10"
        }
        """;

    assertThatThrownBy(() -> consumer.receiveMessage(message))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("DUPLICATE");
  }

  @Test
  void missingActionField() {
    String message = """
        {
            "userId": "ebd971c2-40d4-47c5-bee7-a8868b8d6f71",
            "date": "2014-11-10"
        }
        """;

    assertThatThrownBy(() -> consumer.receiveMessage(message))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Missing action");
  }
}