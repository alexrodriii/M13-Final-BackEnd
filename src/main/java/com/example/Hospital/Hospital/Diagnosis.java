package com.example.Hospital.Hospital;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Diagnosis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String diagnostico;
	private String motivo;
	
	public Diagnosis() {}
	
	public Diagnosis(Integer id, String diagnostico, String motivo) {
		super();
		this.id = id;
		this.diagnostico = diagnostico;
		this.motivo = motivo;
	}

	@Override
	public String toString() {
		return "PatientMedicalData [id=" + id + ", diagnostico=" + diagnostico + ", motivo=" + motivo + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	

}
