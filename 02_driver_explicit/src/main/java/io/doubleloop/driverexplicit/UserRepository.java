package io.doubleloop.driverexplicit;

import io.doubleloop.driverexplicit.domain.GetUserResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// NOTE: not related to driver side, so let's keep it here for now
@Repository
public interface UserRepository extends MongoRepository<GetUserResult.User, String> {
  Optional<GetUserResult.User> findByEmail(String email);
}
