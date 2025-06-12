package com.example.Hospital.Hospital;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.Date;

@RestController
@RequestMapping("/nurse")
public class NurseController {
	@Autowired
	private NurseRepository nurseRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private DiagnosisRepository diagnosisRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private CareRepository careRepository;
	@Autowired
	private RegisterRepository registerRepository;
	public NurseController() {
		super();
	}

	@PostMapping("/login")
	public @ResponseBody ResponseEntity<Optional<Nurse>> login(@RequestBody Nurse nurseLogin) {
		// @ResponseBody Get value from body request
		/*
		 * Optional<Nurse> nurse =
		 * nurseRepository.findByEmailAndPasswordCaseSensitive(nurseLogin.getEmail(),
		 * nurseLogin.getPassword());
		 */
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

	@GetMapping("/diagnosis/{id}")
	public ResponseEntity<Diagnosis> getDiagnosisById(@PathVariable Integer id) {
		Optional<Diagnosis> diagnosis = diagnosisRepository.findById(id);
		if (diagnosis.isPresent()) {
			return ResponseEntity.ok(diagnosis.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/patient/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Integer id) {
		Optional<Patient> patient = patientRepository.findById(id);
		if (patient.isPresent()) {
			return ResponseEntity.ok(patient.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/room/{id}/patients")
	public ResponseEntity<List<Patient>> getPatientsByRoom(@PathVariable("id") String roomId) {
		List<Register> registros = registerRepository.findByRoomId(roomId);
		List<Patient> pacientes = new ArrayList<>();
		for (Register reg : registros) {
			pacientes.add(reg.getPatient());
		}
		return ResponseEntity.ok(pacientes);
	}

	@GetMapping("/patient/{id}/diagnoses")
	public ResponseEntity<List<Diagnosis>> getDiagnosesByPatient(@PathVariable("id") Integer patientId) {
		List<Register> registros = registerRepository.findByPatientId(patientId);
		List<Diagnosis> diagnoses = new ArrayList<>();
		for (Register reg : registros) {
			if (reg.getDiagnosis() != null) {
				diagnoses.add(reg.getDiagnosis());
			}
		}
		return ResponseEntity.ok(diagnoses);
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
	
	@GetMapping("/care/detail/{id}")
	public @ResponseBody ResponseEntity<Optional<Care>> findCareById(@PathVariable("id") int id) {
		Optional<Care> care = careRepository.findById(id);
		// Check if the nurse exist
		if (care.isPresent()) {
			// If the nurse exists then he shows it to us
			return ResponseEntity.ok(care);
		} else {
			// If the nurse doesn't exist it shows error
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("care/{id}")
	public ResponseEntity<List<Care>> getCareByPatient(@PathVariable("id") int patientId) {
		Optional<Patient> patient = patientRepository.findById(patientId); //
		if (patient.isPresent()) { //
			List<Care> cares = careRepository.findByPatient(patient.get()); //
			return ResponseEntity.ok(cares); //
		} else { //
			return ResponseEntity.notFound().build(); //
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
	public ResponseEntity<List<Room>> showRoomsWithPatients() {
		List<Room> rooms = (List<Room>) roomRepository.findAll();
		for (Room room : rooms) {
			List<Register> registers = registerRepository.findByRoomId(room.getId());
			List<Patient> patients = new ArrayList<>();
			for (Register reg : registers) {
				if (reg.getPatient() != null) {
					patients.add(reg.getPatient());
				}
			}
			room.setPatients(patients); // Set the transient field
		}
		return ResponseEntity.ok(rooms);

	}

	@PostMapping("/createNurse")
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

	@PostMapping("/care/{patientId}/{nurseId}")
	public @ResponseBody ResponseEntity<Care> createCare(@PathVariable("patientId") int patientId,@PathVariable("nurseId") int nurseId, @RequestBody Care careUpdate) { //
		Optional<Patient> patientOptional = patientRepository.findById(patientId); 
		Optional<Nurse> nurseOptional =  nurseRepository.findById(nurseId);
		if (patientOptional.isPresent() && nurseOptional.isPresent()) {
			Nurse nurse = nurseOptional.get();
			Patient patient = patientOptional.get(); 
			try {
				Care newCare = new Care(); 
				newCare.setTA_Sistolica(careUpdate.getTA_Sistolica()); 
				newCare.setTA_Distolica(careUpdate.getTA_Distolica());
				newCare.setFreq_resp(careUpdate.getFreq_resp()); 
				newCare.setPols(careUpdate.getPols()); 
				newCare.setTemperatura(careUpdate.getTemperatura());
				newCare.setSaturacio_oxigen(careUpdate.getSaturacio_oxigen());
				newCare.setPatient(patient); 
				newCare.setDate(new Date());
				newCare.setNurse(nurse);
				Care createdCare = careRepository.save(newCare); 
				return ResponseEntity.status(HttpStatus.CREATED).body(createdCare); 
			} catch (Exception e) { 
				return ResponseEntity.badRequest().build(); 
			}
		} else { //
			return ResponseEntity.notFound().build(); 
		}
	}

	@GetMapping("/allCare")
	public @ResponseBody ResponseEntity<Iterable<Care>> showCare() {
		return ResponseEntity.ok((careRepository.findAll()));
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