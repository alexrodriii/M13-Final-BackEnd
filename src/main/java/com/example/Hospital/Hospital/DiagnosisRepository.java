package com.example.Hospital.Hospital;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Integer>{
	List<Register> findByPatientId(Integer patientId);
}
