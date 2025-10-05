package com.frknozbek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingEntityCallback;

import jakarta.validation.Validator;

@Configuration
public class MongoConfiguration {

  @Bean
  public ValidatingEntityCallback validatingEntityCallback(Validator validator) {
    return new ValidatingEntityCallback(validator);
  }
}
