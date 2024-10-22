package com.example.Hospital.Hospital;

import java.util.ArrayList;
import java.util.Optional;

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

	private ArrayList<Nurse> nurses = new ArrayList<Nurse>();
	@Autowired
	private NurseRepository nurseRepository;

	public NurseController() {
		super();
	}

	@PostMapping("/login")
	private @ResponseBody ResponseEntity<Boolean> login(@RequestParam String name, @RequestParam String password) {
		// @ResponseBody Get value from body request
		Optional<Nurse> nurseLogin = nurseRepository.findByNameAndPassword(name, password);

		// Checks if a nurse with the given credentials exists in the database.
		if (nurseLogin.isPresent()) {
			// Compares the password entered by the user with the password in the database
			if (nurseLogin.get().getPassword().equals(password)) {
				return ResponseEntity.ok(true);
			}

		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

	}

	@GetMapping("/nurses")
	public ResponseEntity<ArrayList<Nurse>> getAll() {

		return ResponseEntity.ok(nurses);

	}

	// The method
	@GetMapping("/name/{name}")
	public ResponseEntity<Nurse> findByName(@PathVariable String name) {
		for (Nurse nurse : nurses) {
			if (nurse.getName().equalsIgnoreCase(name)) {
				return ResponseEntity.ok(nurse);

			}
		}
		return ResponseEntity.notFound().build();
	}

}
