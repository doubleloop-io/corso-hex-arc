package io.doubleloop.drivenpush;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringUserRepository extends MongoRepository<User, String>, UserRepository {
}
