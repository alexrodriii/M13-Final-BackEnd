package com.example.Hospital.Hospital;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/nurse")
public class NurseController {
	@Autowired
	private NurseRepository nurseRepository;

	private ArrayList<Nurse> nurses = new ArrayList<Nurse>();

	public NurseController() {
		super();
	}

	@PostMapping("/login")
	private @ResponseBody ResponseEntity<Boolean> login(@RequestParam String name, @RequestParam String password) {
		// @ResponseBody Get value from body request
		Optional<Nurse> nurseLogin = nurseRepository.findByNameAndPasswordCaseSensitive(name, password);

		// Checks if a nurse with the given credentials exists in the database.
		if (nurseLogin.isPresent()) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

	}

	@GetMapping("/nurses")
	public @ResponseBody ResponseEntity<Iterable<Nurse>> getAll() {

		return ResponseEntity.ok((nurseRepository.findAll()));

	}

	// The method
	@GetMapping("/name/{name}")
	public @ResponseBody ResponseEntity<Optional<Nurse>> findByName(@PathVariable("name") String name) {
		Optional<Nurse> nurse = nurseRepository.findByNameIgnoringCase(name);
		if (nurse.isPresent()) {
			return ResponseEntity.ok(nurse);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/deleteNurse/{id}")
	public @ResponseBody ResponseEntity<Boolean> deleteNurseById(@PathVariable("id") int id) {
		// Check if the id of a nurse exist
		if (nurseRepository.existsById(id)) {
			// Delete a specific nurse
			nurseRepository.deleteById(id);
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		}
	}

}
