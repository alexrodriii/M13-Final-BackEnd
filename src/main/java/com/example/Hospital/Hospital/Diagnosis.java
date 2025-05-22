package com.example.Hospital.Hospital;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Diagnosis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String diagnostico;
	private String motivo;
	 @Column(columnDefinition = "TEXT")
	    private String portadorO2Tipus;

	    @Column
	    private Boolean portadorBolquer;

	    @Column
	    private Integer numeroCanvisBolquer;

	    @Column(columnDefinition = "TEXT")
	    private String estatPell;


	@ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
	
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
