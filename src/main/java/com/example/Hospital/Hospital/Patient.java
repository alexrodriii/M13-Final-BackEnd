package com.example.Hospital.Hospital;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Pacientes")
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Patient_id")
    private Integer id;



	@Column(name = "Patient_Name")
    private String name;

    @Column(name = "Patient_DNI")
    private String dni;

    @Column(name = "Patient_Telefono")
    private String telefono;

    @Column(name = "Patient_Correo")
    private String correo;

    @Column(name = "Patient_Direccion")
    private String direccion;

    public Patient() {
    }

    public Patient(Integer id, String name, String dni, String telefono, String correo, String direccion) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    // Getters y Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", name=" + name + ", dni=" + dni + ", telefono=" + telefono +
               ", correo=" + correo + ", direccion=" + direccion + "]";
    }
}
