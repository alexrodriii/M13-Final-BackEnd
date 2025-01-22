package com.example.Hospital.Hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		// Load file .env
		Dotenv dotenv = Dotenv.configure().load();

		// Use the variable save it in the file
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(HospitalApplication.class, args);
	}

}
