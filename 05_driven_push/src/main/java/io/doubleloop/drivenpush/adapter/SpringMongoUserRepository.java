package io.doubleloop.drivenpush.adapter;

import io.doubleloop.drivenpush.domain.User;
import io.doubleloop.drivenpush.domain.UserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringMongoUserRepository extends MongoRepository<User, String>, UserRepository {
}
