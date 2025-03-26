package com.example.Hospital.Hospital;

import jakarta.persistence.*;


@Entity(name="Habitaciones")
public class Room {
	@Id
	@Column(name="Hab_id")
	private String id;
	

	@Column(name="Hab_Obs")
	private String observations;
	
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
