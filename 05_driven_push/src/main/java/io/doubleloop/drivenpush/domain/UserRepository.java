package io.doubleloop.drivenpush.domain;

import java.util.Optional;

public interface UserRepository {
  Optional<User> findById(String id);
}
