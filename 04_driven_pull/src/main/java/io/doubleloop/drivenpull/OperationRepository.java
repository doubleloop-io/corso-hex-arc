package io.doubleloop.drivenpull;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface OperationRepository extends MongoRepository<Operation, String> {
  List<Operation> findByUserIdAndDate(String userId, LocalDate date);
}
