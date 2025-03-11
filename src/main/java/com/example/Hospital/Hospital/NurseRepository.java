package com.example.Hospital.Hospital;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NurseRepository extends CrudRepository<Nurse, Integer> {
	@Query(value = "SELECT * FROM nurse WHERE email = :email AND BINARY password = :password", nativeQuery = true)
	Optional<Nurse> findByEmailAndPasswordCaseSensitive(@Param("email") String email,
			@Param("password") String password);
	Optional<Nurse> findByNameIgnoringCase(String name);
	Optional<Nurse> findByNurseNumber(Integer nurseNumber);

}
