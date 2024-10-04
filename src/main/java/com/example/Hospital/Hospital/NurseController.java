package com.example.Hospital.Hospital;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nurse")
public class NurseController {

	ArrayList<Nurse> nurses = new ArrayList<Nurse>();

	public NurseController() {
		super();
		nurses.add(new Nurse("Noemi", "Azul", 21, "Farmacoterapia", 1));
		nurses.add(new Nurse("Alex" , "Verde", 19,"Oftalmología",2));
		nurses.add(new Nurse("Dafne", "Amarillo", 20, "Dermatología", 3));
	}

	// The method
	@GetMapping("/name/{name}")
	public ResponseEntity<Nurse> findByName(@PathVariable  String name) {
		for (Nurse nurse : nurses) {
			if (nurse.getName().equals(name)) {
				return ResponseEntity.ok(nurse);
				
			}
		}
		return ResponseEntity.notFound().build();
	}

}
