package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ExempleRabbitmqApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ExempleRabbitmqApplication.class, args);
	}

	public void run(String... args) throws Exception {
		log.info("Configuration queue and exchange initial");
		
	}

}
