package com.example.medium_clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MediumCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediumCloneApplication.class, args);
	}

}
