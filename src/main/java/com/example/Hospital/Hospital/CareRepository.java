package com.example.Hospital.Hospital;

import java.util.List; // Importar List

import org.springframework.data.repository.CrudRepository;

public interface CareRepository extends CrudRepository<Care, Integer> {
    List<Care> findByPatient(Patient patient); // Añadir esta línea
}