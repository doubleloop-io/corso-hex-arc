package io.doubleloop.drivenpull.adapter;

import io.doubleloop.drivenpull.domain.Operation;
import io.doubleloop.drivenpull.domain.OperationRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringMongoOperationRepository extends MongoRepository<Operation, String>, OperationRepository {
}
