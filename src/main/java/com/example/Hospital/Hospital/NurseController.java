package com.example.Hospital.Hospital;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/nurse")
public class NurseController {
	@Autowired
	private NurseRepository nurseRepository;
	@Autowired
	private RoomRepository roomRepository;

	public NurseController() {
		super();
	}

	@PostMapping("/login")
	public @ResponseBody ResponseEntity<Optional<Nurse>> login(@RequestBody Nurse nurseLogin) {
		// @ResponseBody Get value from body request
		/*Optional<Nurse> nurse = nurseRepository.findByEmailAndPasswordCaseSensitive(nurseLogin.getEmail(),
				nurseLogin.getPassword());*/
		Optional<Nurse> nurse = nurseRepository.findByNurseNumber(nurseLogin.getNurseNumber());
		// Checks if a nurse with the given credentials exists in the database.
		if (nurse.isPresent()) {
			return ResponseEntity.ok(nurse);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

	}

	@GetMapping("/allNurse")
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
		// Check if the nurse exist
		if (nurse.isPresent()) {
			// If the nurse exists then he shows it to us
			return ResponseEntity.ok(nurse);
		} else {
			// If the nurse doesn't exist it shows error
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
		// Validate password format using regex
		String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$";
		if (nurseUpdate.getPassword() != null && !Pattern.matches(passwordRegex, nurseUpdate.getPassword())) {
			// Return 400 if password is invalid
			return ResponseEntity.badRequest().build();
		}

		Optional<Nurse> nurse = nurseRepository.findById(id);
		// Check if the id of nurse exist in the database
		if (nurse.isPresent()) {
			// Create a new nurse that will have the new data to be updated
			Nurse existingNurse = nurse.get();
			// Check if the user pass a name to update the data
			if (nurseUpdate.getName() != null) {
				existingNurse.setName(nurseUpdate.getName());
			}
			if (nurseUpdate.getSurname() != null) {
				existingNurse.setSurname(nurseUpdate.getSurname());
			}
			if (nurseUpdate.getEmail() != null) {
				existingNurse.setEmail(nurseUpdate.getEmail());
			}
			if (nurseUpdate.getPassword() != null) {
				existingNurse.setPassword(nurseUpdate.getPassword());
			}
			if (nurseUpdate.getAge() != null) {
				try {
					LocalDate birthDate = LocalDate.parse(nurseUpdate.getAge(),
							DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					if (LocalDate.now().getYear() - birthDate.getYear() >= 18) {
						existingNurse.setAge(nurseUpdate.getAge());
					}
				} catch (DateTimeParseException e) {
					return ResponseEntity.badRequest().build(); // Invalid date format
				}
			}
			if (nurseUpdate.getSpeciality() != null) {
				existingNurse.setSpeciality(nurseUpdate.getSpeciality());
			}

			try {
				nurseRepository.save(existingNurse);
				return ResponseEntity.ok(existingNurse);
			} catch (Exception e) {
				// Catch any exception and return 400
				return ResponseEntity.badRequest().build();
			}
		} else {
			// If the id pass by the user not exist return 404
			return ResponseEntity.notFound().build();
		}

	}
	
	@GetMapping("/allRoom")
	public @ResponseBody ResponseEntity<Iterable<Room>> showRooms() {
		
		return ResponseEntity.ok((roomRepository.findAll()));
		
	}

	
	@PostMapping()
	public @ResponseBody ResponseEntity<Nurse> createNurse(@RequestBody Nurse nurseCreate) {
		// Validate password format using regex
		String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$";
		if (!Pattern.matches(passwordRegex, nurseCreate.getPassword())) {
			// Return 400 if password is invalid
			return ResponseEntity.badRequest().build();
		}
		try {
			Nurse nurse = new Nurse();
			nurse.setName(nurseCreate.getName());
			nurse.setSurname(nurseCreate.getSurname());
			nurse.setEmail(nurseCreate.getEmail());
			nurse.setAge(nurseCreate.getAge());
			nurse.setPassword(nurseCreate.getPassword());
			nurse.setSpeciality(nurseCreate.getSpeciality());
			nurse.setNurseNumber(nurseCreate.getNurseNumber());
			// Save the data of the new nurse into database
			Nurse createdNurse = nurseRepository.save(nurse);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdNurse);
		} catch (Exception e) {
			// Also return 400 if data saving fails
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/photo/{id}")
	public ResponseEntity<Boolean> uploadPhoto(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
		Optional<Nurse> nurseOptional = nurseRepository.findById(id);
		if (nurseOptional.isPresent()) {
			try {
				Nurse nurse = nurseOptional.get();
				nurse.setPhoto(file.getBytes());
				nurseRepository.save(nurse);

				return ResponseEntity.ok(true);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		}
	}

	@GetMapping("/photo/{id}")
	public ResponseEntity<byte[]> getPhoto(@PathVariable("id") int id) {
		Optional<Nurse> nurseOptional = nurseRepository.findById(id);
		if (nurseOptional.isPresent()) {
			if (nurseOptional.get().getPhoto() != null) {
				return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(nurseOptional.get().getPhoto());

			} else {
				return ResponseEntity.noContent().build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}