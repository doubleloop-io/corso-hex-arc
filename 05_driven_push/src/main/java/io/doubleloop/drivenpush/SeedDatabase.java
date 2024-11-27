package io.doubleloop.drivenpush;

import io.doubleloop.drivenpush.adapter.SpringMongoUserRepository;
import io.doubleloop.drivenpush.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedDatabase implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(SeedDatabase.class);

  private final SpringMongoUserRepository repository;

  public SeedDatabase(SpringMongoUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(String... args) throws Exception {
    repository.deleteAll();

    final var u1 = repository.save(new User("foo@bar.it", false));
    log.info("User 1: {}", u1);

    final var u2 = repository.save(new User("devil@corp.com", true));
    log.info("User 2: {}", u2);
  }
}
