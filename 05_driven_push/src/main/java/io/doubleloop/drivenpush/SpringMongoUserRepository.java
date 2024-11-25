package io.doubleloop.drivenpush;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringMongoUserRepository extends MongoRepository<User, String>, UserRepository {
}
