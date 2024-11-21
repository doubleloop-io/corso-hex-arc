package io.doubleloop.bank.adapter;

import io.doubleloop.bank.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringMongoAccountRepository extends MongoRepository<Account, String> {
}
