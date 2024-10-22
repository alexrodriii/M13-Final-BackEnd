package com.example.Hospital.Hospital;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface NurseRepository extends CrudRepository<Nurse, Integer> {
	Optional<Nurse> findByNameAndPassword(String username, String password);
}
