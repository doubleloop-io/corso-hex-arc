package io.doubleloop.driverreactive.domain;

public interface WorkRecordService {
  void onAddWorkRecord(AddWorkRecordCommand command);
  void onRemoveWorkRecord(RemoveWorkRecordCommand command);
}
