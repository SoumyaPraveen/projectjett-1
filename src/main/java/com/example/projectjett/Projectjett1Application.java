package com.example.projectjett;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class Projectjett1Application {

	public static void main(String[] args) {
		SpringApplication.run(Projectjett1Application.class, args);
	}
}
