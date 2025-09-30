package com.frknozbek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.mapping.event.ValidatingEntityCallback;

import com.mongodb.client.MongoClients;

import jakarta.validation.Validator;

@Configuration
public class MongoConfiguration {

  @Bean
  public MongoDatabaseFactory mongoDatabaseFactory() {
    return new SimpleMongoClientDatabaseFactory(MongoClients.create(), "gallerist");
  }

  @Bean
  public ValidatingEntityCallback validatingEntityCallback(Validator validator) {
    return new ValidatingEntityCallback(validator);
  }
}
