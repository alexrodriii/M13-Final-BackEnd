package com.example.Hospital.Hospital;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="Pacientes")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Patient_id")
	private String id;

	@Column(name="Patient_Name")
	private String name;
	
	public Patient() {}
	

	public Patient(String id, String name) {
		this.id = id;
		this.name = name;
		
	}


	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	} 
	
}
