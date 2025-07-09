package com.zorio.ppb.marketupdatesconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories(basePackages = "com.zorio.ppb.marketupdatesconsumer.repositories")
public class MarketUpdatesConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketUpdatesConsumerApplication.class, args);
	}

}
