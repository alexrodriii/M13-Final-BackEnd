package com.example.Hospital.Hospital;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

class NurseControllerTest {

	@Mock
	private NurseRepository nurseRepository;

	@InjectMocks
	private NurseController nurseController;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(nurseController).build();
	}

	@Test
	void testLogin_Success() {
		String name = "John";
		String password = "Password123";
		when(nurseRepository.findByNameAndPasswordCaseSensitive(name, password)).thenReturn(Optional.of(new Nurse()));

		ResponseEntity<Boolean> response = nurseController.login(name, password);

		assertEquals(200, response.getStatusCodeValue());
		assertTrue(response.getBody());
	}

	@Test
	void testLogin_Unauthorized() {
		String name = "John";
		String password = "WrongPassword";
		when(nurseRepository.findByNameAndPasswordCaseSensitive(name, password)).thenReturn(Optional.empty());

		ResponseEntity<Boolean> response = nurseController.login(name, password);

		assertEquals(401, response.getStatusCodeValue());
		assertFalse(response.getBody());
	}

	@Test
	void testGetAll() {
		when(nurseRepository.findAll()).thenReturn(new ArrayList<>());

		ResponseEntity<Iterable<Nurse>> response = nurseController.getAll();

		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
	}

	@Test
	void testFindByName_Found() {
		String name = "Jane";
		Nurse nurse = new Nurse();
		when(nurseRepository.findByNameIgnoringCase(name)).thenReturn(Optional.of(nurse));

		ResponseEntity<Optional<Nurse>> response = nurseController.findByName(name);

		assertEquals(200, response.getStatusCodeValue());
		assertTrue(response.getBody().isPresent());
	}

	@Test
	void testFindByName_NotFound() {
		String name = "Unknown";
		when(nurseRepository.findByNameIgnoringCase(name)).thenReturn(Optional.empty());

		ResponseEntity<Optional<Nurse>> response = nurseController.findByName(name);

		assertEquals(404, response.getStatusCodeValue());
		assertNull(response.getBody());
	}

	@Test
	void testFindById_Found() {
		int id = 1;
		Nurse nurse = new Nurse("John Doe", 30, "password123", "Cardiology");

		// Mock the repository to return a nurse for the given ID
		when(nurseRepository.findById(id)).thenReturn(Optional.of(nurse));

		// Call the controller method
		ResponseEntity<Optional<Nurse>> response = nurseController.finById(id);

		// Assert the response status is 200 OK
		assertEquals(200, response.getStatusCodeValue());

		// Assert the nurse is returned in the response body
		assertTrue(response.getBody().isPresent());
		assertEquals(nurse, response.getBody().get()); // Nurse data should match
	}

	@Test
	void testFindById_NotFound() {
		int id = 999; // Assuming no nurse exists with this ID

		// Mock the repository to return an empty Optional (nurse not found)
		when(nurseRepository.findById(id)).thenReturn(Optional.empty());

		// Call the controller method
		ResponseEntity<Optional<Nurse>> response = nurseController.finById(id);

		// Assert the response status is 404 Not Found
		assertEquals(404, response.getStatusCodeValue());

		// Assert that the response body is null, as nurse was not found
		assertNull(response.getBody());
	}

	@Test
	void testDeleteNurseById_Success() {
		int id = 1;
		when(nurseRepository.existsById(id)).thenReturn(true);

		ResponseEntity<Boolean> response = nurseController.deleteNurseById(id);

		assertEquals(200, response.getStatusCodeValue());
		assertTrue(response.getBody());
		verify(nurseRepository, times(1)).deleteById(id);
	}

	@Test
	void testDeleteNurseById_NotFound() {
		int id = 1;
		when(nurseRepository.existsById(id)).thenReturn(false);

		ResponseEntity<Boolean> response = nurseController.deleteNurseById(id);

		assertEquals(404, response.getStatusCodeValue());
		assertFalse(response.getBody());
	}

	@Test
	void testCreateNurse_Success() {
		String name = "John";
		String password = "Valid123";
		int age = 30;
		String speciality = "Cardiology";

		Nurse nurse = new Nurse();
		nurse.setName(name);
		nurse.setPassword(password);
		nurse.setAge(age);
		nurse.setSpeciality(speciality);

		when(nurseRepository.save(any(Nurse.class))).thenReturn(nurse);

		ResponseEntity<Nurse> response = nurseController.createNurse(name, password, age, speciality);

		assertEquals(201, response.getStatusCodeValue()); // Ensure status is 201 Created
		assertNotNull(response.getBody());
		assertEquals(name, response.getBody().getName());
		assertEquals(password, response.getBody().getPassword());
		assertEquals(age, response.getBody().getAge());
		assertEquals(speciality, response.getBody().getSpeciality());
	}

	@Test
	void testCreateNurse_Failed_Exception() {
		String name = "John";
		String password = "Valid123"; // Valid password
		int age = 30;
		String speciality = "Cardiology";

		// Simulate an exception when saving the nurse
		doThrow(new RuntimeException("Database Error")).when(nurseRepository).save(any(Nurse.class));

		// Call the controller method
		ResponseEntity<Nurse> response = nurseController.createNurse(name, password, age, speciality);

		// Verify that the response status is 400 Bad Request
		assertEquals(400, response.getStatusCodeValue());

		// Verify that the response body is null (as no nurse was created)
		assertNull(response.getBody());
	}

	@Test
	void testUpdateNurse_Success() {
		int id = 1;
		// Valid password format and valid update data
		Nurse updatedNurse = new Nurse("Jane Doe", 30, "NewPassword123", "Neurology");

		// Mocking findById to return an existing nurse
		when(nurseRepository.findById(id))
				.thenReturn(Optional.of(new Nurse("John Doe", 30, "password123", "Cardiology")));

		// Mocking save to return the updated nurse
		when(nurseRepository.save(any(Nurse.class))).thenReturn(updatedNurse);

		// Calling the controller method
		ResponseEntity<Nurse> response = nurseController.updateNurse(id, updatedNurse);

		// Assert the response status is 200 OK
		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody()); // Body should not be null
		assertEquals("Jane Doe", response.getBody().getName()); // Assert name is updated
		assertEquals("Neurology", response.getBody().getSpeciality()); // Assert speciality is updated
	}

	@Test
	void testUpdateNurse_NotFound() {
		int id = 1;
		Nurse nurseUpdate = new Nurse("John Updated", 28, "Valid123", "Surgery");

		when(nurseRepository.findById(id)).thenReturn(Optional.empty());

		ResponseEntity<Nurse> response = nurseController.updateNurse(id, nurseUpdate);

		assertEquals(404, response.getStatusCodeValue());
		assertNull(response.getBody()); // Body should be null for 404
	}

	@Test
	void testUpdateNurse_Exception() {
		int id = 1;
		Nurse nurseUpdate = new Nurse("John Updated", 28, "Valid123", "Surgery");

		when(nurseRepository.findById(id)).thenReturn(Optional.of(new Nurse()));
		doThrow(new RuntimeException("Database Error")).when(nurseRepository).save(any(Nurse.class));

		ResponseEntity<Nurse> response = nurseController.updateNurse(id, nurseUpdate);

		assertEquals(400, response.getStatusCodeValue());
		assertNull(response.getBody());
	}

}
