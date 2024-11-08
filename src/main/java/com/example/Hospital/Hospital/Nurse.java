package com.example.Hospital.Hospital;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Nurse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idNurse;

	private String name;
	private String password;
	private int age;
	private String speciality;

	public Nurse(String name, int age, String password, String speciality) {
		this.name = name;
		this.age = age;
		this.password = password;
		this.speciality = speciality;
	}

	public Nurse() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Integer getIdNurse() {
		return idNurse;
	}

	@Override
	public String toString() {
		return "Nurse{" + "Name='" + name + '\'' + ", Age=" + age + '\'' + ", idNurse='" + idNurse + '\''
				+ ", speciality='" + speciality + '\'' + '}';

	}

}
