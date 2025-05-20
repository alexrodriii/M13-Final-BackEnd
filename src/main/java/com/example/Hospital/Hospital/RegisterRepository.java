package com.example.Hospital.Hospital;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends CrudRepository<Register, Integer> {
	List<Register> findByRoomId(String id);
	List<Register> findByPatientId(Integer id);
}
