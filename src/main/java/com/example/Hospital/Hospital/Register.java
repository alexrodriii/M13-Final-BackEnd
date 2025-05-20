package com.example.Hospital.Hospital;

import jakarta.persistence.*;

@Entity
@Table(name = "registro")
public class Register {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reg_id")
    private int id;

    @ManyToOne 
    @JoinColumn(name = "care_id")
    private Care care;

    @OneToOne
    @JoinColumn(name = "diag_id")
    private Diagnosis diagnosis;

    @ManyToOne
    @JoinColumn(name = "hab_id")
    private Room room;

    @OneToOne
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;
    
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

    public void setId(int id) {
        this.id = id;
    }

    public Care getCare() {
        return care;
    }

    public void setCare(Care care) {
        this.care = care;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }
}
