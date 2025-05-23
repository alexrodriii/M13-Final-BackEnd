package com.example.Hospital.Hospital;

import java.util.List;

import jakarta.persistence.*;


@Entity(name="Habitaciones")
public class Room {
	@Id
	@Column(name="Hab_id")
	private String id;
	

	@Column(name="Hab_Obs")
	private String observations;
	
	@Transient
	private List<Patient> patients;
	
	public List<Patient> getPatients() {
		return patients;
	}


	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}


	public Room() {}
	
	
	public Room(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	
	
}
