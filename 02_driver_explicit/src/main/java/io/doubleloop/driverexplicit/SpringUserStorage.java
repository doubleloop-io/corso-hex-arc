package io.doubleloop.driverexplicit;

import io.doubleloop.driverexplicit.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpringUserStorage extends MongoRepository<User, String> {
  Optional<User> findByEmail(String email);
}
