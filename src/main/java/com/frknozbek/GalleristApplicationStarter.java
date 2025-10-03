package com.frknozbek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
