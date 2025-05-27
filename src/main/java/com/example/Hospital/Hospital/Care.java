package com.example.Hospital.Hospital;

import jakarta.persistence.*;

@Entity
public class Care {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Añadir esta línea
	@Column(name="Cv_id")
	private int id;
	@Column
	private int TA_Sistolica;
	@Column
	private int freq_resp;
	@Column
	private int pols;
	@Column
	private int temperatura;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    
	
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public int getId() {
		return id;
	}
	public int getFreq_resp() {
		return freq_resp;
	}
	public void setFreq_resp(int freq_resp) {
		this.freq_resp = freq_resp;
	}
	public int getPols() {
		return pols;
	}
	public void setPols(int pols) {
		this.pols = pols;
	}
	public int getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
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
