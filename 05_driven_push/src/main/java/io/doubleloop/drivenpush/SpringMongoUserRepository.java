package io.doubleloop.drivenpush;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringMongoUserRepository extends MongoRepository<User, String>, UserRepository {
}
