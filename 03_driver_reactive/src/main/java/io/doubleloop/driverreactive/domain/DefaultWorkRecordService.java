package io.doubleloop.driverreactive.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// NOTE: Just an empty implementation
@Service
public class DefaultWorkRecordService implements WorkRecordService {

  private static final Logger log = LoggerFactory.getLogger(DefaultWorkRecordService.class);

  @Override
  public void onAddWorkRecord(AddWorkRecordCommand command) {
    log.info("Adding work record: {}", command);
  }

  @Override
  public void onRemoveWorkRecord(RemoveWorkRecordCommand command) {
    log.info("Removing work record: {}", command);
  }
}
