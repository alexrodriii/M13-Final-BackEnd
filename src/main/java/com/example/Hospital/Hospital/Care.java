package com.example.Hospital.Hospital;

import jakarta.persistence.*;

@Entity
public class Care {
	@Id
	@Column(name="Cv_id")
	private int id;
	@Column
	private int TA_Sistolica;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTA_Sistolica() {
		return TA_Sistolica;
	}
	public void setTA_Sistolica(int ta_Sistolica) {
		TA_Sistolica = ta_Sistolica;
	}
	
	
}
