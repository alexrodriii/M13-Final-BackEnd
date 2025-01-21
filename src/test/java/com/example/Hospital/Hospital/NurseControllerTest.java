package com.example.Hospital.Hospital;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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
		Nurse loginNurse = new Nurse();
		loginNurse.setEmail("test@test.com");
		loginNurse.setPassword("Test123");

		when(nurseRepository.findByEmailAndPasswordCaseSensitive(loginNurse.getEmail(), loginNurse.getPassword()))
				.thenReturn(Optional.of(new Nurse()));

		ResponseEntity<Optional<Nurse>> response = nurseController.login(loginNurse);

		assertEquals(200, response.getStatusCodeValue());
		assertTrue(response.getBody().isPresent());
	}

	@Test
	void testLogin_Unauthorized() {
		Nurse loginNurse = new Nurse();
		loginNurse.setEmail("test@test.com");
		loginNurse.setPassword("WrongPassword");
		when(nurseRepository.findByEmailAndPasswordCaseSensitive(loginNurse.getEmail(), loginNurse.getPassword()))
				.thenReturn(Optional.empty());

		ResponseEntity<Optional<Nurse>> response = nurseController.login(loginNurse);

		assertEquals(401, response.getStatusCodeValue());
		assertNull(response.getBody()); // Verificamos que el cuerpo sea nulo
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

		assertEquals(404, response.getStatusCodeValue()); // Verificar código de estado
		assertNull(response.getBody()); // Verificar que el cuerpo sea null
	}

	@Test
	void testFindById_Found() {
		int id = 1;
		Nurse nurse = new Nurse();
		when(nurseRepository.findById(id)).thenReturn(Optional.of(nurse));

		ResponseEntity<Optional<Nurse>> response = nurseController.finById(id);

		assertEquals(200, response.getStatusCodeValue());
		assertTrue(response.getBody().isPresent());
	}

	@Test
	void testFindById_NotFound() {
		int id = 999; // ID que no existe
		when(nurseRepository.findById(id)).thenReturn(Optional.empty());

		ResponseEntity<Optional<Nurse>> response = nurseController.finById(id);

		assertEquals(404, response.getStatusCodeValue()); // Verificar código de estado
		assertNull(response.getBody()); // Verificar que el cuerpo sea null
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
		Nurse nurse = new Nurse();
		nurse.setName("John");
		nurse.setPassword("Valid123");
		nurse.setAge("01/01/1990");
		nurse.setSpeciality("Cardiology");

		when(nurseRepository.save(any(Nurse.class))).thenReturn(nurse);

		ResponseEntity<Nurse> response = nurseController.createNurse(nurse);

		assertEquals(201, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals(nurse.getName(), response.getBody().getName());
	}

	@Test
	void testCreateNurse_Failed_InvalidPassword() {
		Nurse nurse = new Nurse();
		nurse.setName("John");
		nurse.setPassword("invalid");
		nurse.setAge("01/01/1990");
		nurse.setSpeciality("Cardiology");

		ResponseEntity<Nurse> response = nurseController.createNurse(nurse);

		assertEquals(400, response.getStatusCodeValue());
		assertNull(response.getBody());
	}

	@Test
	void testUpdateNurse_Success() {
		int id = 1;
		Nurse existingNurse = new Nurse();
		existingNurse.setName("John");
		existingNurse.setPassword("Valid123");

		Nurse updatedNurse = new Nurse();
		updatedNurse.setName("John Updated");
		updatedNurse.setPassword("Valid456");

		when(nurseRepository.findById(id)).thenReturn(Optional.of(existingNurse));
		when(nurseRepository.save(any(Nurse.class))).thenReturn(updatedNurse);

		ResponseEntity<Nurse> response = nurseController.updateNurse(id, updatedNurse);

		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals(updatedNurse.getName(), response.getBody().getName());
	}

	@Test
	void testUpdateNurse_NotFound() {
		int id = 1;
		Nurse updatedNurse = new Nurse();
		updatedNurse.setName("John Updated");

		when(nurseRepository.findById(id)).thenReturn(Optional.empty());

		ResponseEntity<Nurse> response = nurseController.updateNurse(id, updatedNurse);

		assertEquals(404, response.getStatusCodeValue());
		assertNull(response.getBody());
	}
}
