package com.frknozbek.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.frknozbek"})
@SpringBootApplication
public class GalleristApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(GalleristApplicationStarter.class, args);

		// MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "gallerist");

		// MongoJsonSchema schema = MongoJsonSchemaCreator.create(mongoOps.getConverter())
		// 		.createSchemaFor(Account.class);

		// mongoOps.createCollection(Account.class, CollectionOptions.empty().schema(schema));

	}

}
