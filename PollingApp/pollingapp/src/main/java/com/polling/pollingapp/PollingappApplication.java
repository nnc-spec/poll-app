package com.polling.pollingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.polling.pollingapp.model")
public class PollingappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollingappApplication.class, args);
	}

}
