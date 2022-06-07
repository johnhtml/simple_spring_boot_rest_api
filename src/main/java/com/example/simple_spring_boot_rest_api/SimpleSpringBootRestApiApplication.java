package com.example.simple_spring_boot_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleSpringBootRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringBootRestApiApplication.class, args);
	}


	// Note
	// get a light version of the Docker postgres image with
	// docker run --name postgres-spring -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine

	// # psql -U postgres
}
