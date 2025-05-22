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

    @Column(name = "portadoro2tipus")
    private String portadorO2Tipus;

    @Column
    private Boolean portadorBolquer;

    @Column
    private Integer numeroCanvisBolquer;

    @Column(name = "estat_pell")
    private String estatPell;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Diagnosis() {}

    public Diagnosis(Integer id, String diagnostico, String motivo) {
        this.id = id;
        this.diagnostico = diagnostico;
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Diagnosis [id=" + id + ", diagnostico=" + diagnostico + ", motivo=" + motivo + "]";
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

    public String getPortadorO2Tipus() {
        return portadorO2Tipus;
    }

    public void setPortadorO2Tipus(String portadorO2Tipus) {
        this.portadorO2Tipus = portadorO2Tipus;
    }

    public Boolean getPortadorBolquer() {
        return portadorBolquer;
    }

    public void setPortadorBolquer(Boolean portadorBolquer) {
        this.portadorBolquer = portadorBolquer;
    }

    public Integer getNumeroCanvisBolquer() {
        return numeroCanvisBolquer;
    }

    public void setNumeroCanvisBolquer(Integer numeroCanvisBolquer) {
        this.numeroCanvisBolquer = numeroCanvisBolquer;
    }

    public String getEstatPell() {
        return estatPell;
    }

    public void setEstatPell(String estatPell) {
        this.estatPell = estatPell;
    }

   
}
