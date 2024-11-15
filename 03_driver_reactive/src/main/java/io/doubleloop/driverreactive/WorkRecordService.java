package io.doubleloop.driverreactive;

public interface WorkRecordService {
  void onAddWorkRecord(AddWorkRecordCommand command);
  void onRemoveWorkRecord(RemoveWorkRecordCommand command);
}
