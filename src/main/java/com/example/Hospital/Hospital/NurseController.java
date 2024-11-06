package com.example.Hospital.Hospital;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

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

	@GetMapping
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
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Optional<Nurse>> finById(@PathVariable("id") int id) {
		Optional<Nurse> nurse = nurseRepository.findById(id);
		if (nurse.isPresent()) {
			return ResponseEntity.ok(nurse);
		} else {
			return ResponseEntity.notFound().build();
    }
	}

	@DeleteMapping("/{id}")
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


	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Nurse> updateNurse(@PathVariable int id, @RequestBody Nurse nurseUpdate) {
		Optional<Nurse> nurse = nurseRepository.findById(id);
		// Check if the id of nurse exist in the database
		if (nurse.isPresent()) {
			try {
				// Create a new nurse that will have the new data to be updated
				Nurse updatedNurse = new Nurse(id, nurseUpdate.getName(), nurseUpdate.getAge(),
						nurseUpdate.getPassword(), nurseUpdate.getSpeciality());

				nurseRepository.save(updatedNurse);
				return ResponseEntity.ok(updatedNurse);
			} catch (Exception e) {
				// Catch any exception and return 400
				return ResponseEntity.badRequest().build();
			}
		} else {
			// If the id pass by the user not exist return 404
			return ResponseEntity.notFound().build();
		}
	}

}
