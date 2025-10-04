package com.frknozbek.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.frknozbek.model.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findByAccountNo(String accountNo);

}
