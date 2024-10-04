package com.example.Hospital.Hospital;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nurse")
public class NurseController {

	private ArrayList<Nurse> nurses = new ArrayList<Nurse>();

	public NurseController() {
		super();
		nurses.add(new Nurse("Pedro", "Pass0", 25, "Enfermero"));
		nurses.add(new Nurse("Antonio", "Pass1", 38, "Cardiologia"));
		nurses.add(new Nurse("Alexandra", "Pass2", 40, "Cardiologia"));
		nurses.add(new Nurse("Carla", "Pass3", 48, "Laboratorio"));
		nurses.add(new Nurse("Jhon", "Pass4", 36, "Dermatologo"));
	}

	@PostMapping("/login")
	private @ResponseBody ResponseEntity<Boolean> login(@RequestParam String name, @RequestParam String password) {
		// @ResponseBody Get value from body request
		for (Nurse nurse : nurses) {
			if (name.equalsIgnoreCase(nurse.getName()) && password.equals(nurse.getPassword())) {
				return ResponseEntity.ok(true);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
	}
}
