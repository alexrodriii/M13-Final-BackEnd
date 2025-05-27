package com.example.Hospital.Hospital;

import java.util.List;

import jakarta.persistence.*;


@Entity(name="Habitaciones")
public class Room {
	@Id
	@Column(name="Hab_id")
	private String id;
	@Column
	private int number;

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

	
	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
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
