package io.doubleloop.drivenpush;

import java.util.Optional;

public interface UserRepository {
  Optional<User> findById(String id);
}
