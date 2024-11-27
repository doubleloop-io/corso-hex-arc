package io.doubleloop.drivenpull.domain;

import java.time.LocalDate;
import java.util.List;

public interface OperationRepository {
  List<Operation> findByUserIdAndDate(String userId, LocalDate date);
}
