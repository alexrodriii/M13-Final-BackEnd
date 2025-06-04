package com.example.Hospital.Hospital;

import java.util.Date;
import jakarta.persistence.*;

@Entity
public class Care {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Añadir esta línea
	@Column(name = "Cv_id")
	private int id;
	@Column
	private int TA_Sistolica;
	@Column
	private int freq_resp;
	@Column
	private int pols;
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Column(name = "Saturacio_oxigen")
	private int Saturacio_oxigen;
	@Column
	private Double temperatura;
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "nurse_id")
	private Nurse nurse;

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

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

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
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
	
	public int getSaturacio_oxigen() {
		return Saturacio_oxigen;
	}

	public void setSaturacio_oxigen(int saturacio_oxigen) {
		Saturacio_oxigen = saturacio_oxigen;
	}

}
