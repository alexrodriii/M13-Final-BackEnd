package com.example.Hospital.Hospital;

import java.util.Arrays;
import jakarta.persistence.*;

@Entity
public class Nurse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer nurseNumber;
	private String name;
	private String surname;
	private String age;
	private String email;
	private String password;
	private String speciality;

	@Lob
	@Column(columnDefinition = "LONGBLOB", nullable = true)
	private byte[] photo;
	
	public Nurse() {}

	public Nurse(String name, String surname, String age, String email, String password, String speciality, Integer nurseNumber) {
		super();
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.nurseNumber = nurseNumber;
		this.email = email;
		this.password = password;
		this.speciality = speciality;
	}

	public Integer getNurseNumber() {
		return nurseNumber;
	}

	public void setNurseNumber(Integer nurseNumber) {
		this.nurseNumber = nurseNumber;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
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

	@Override
	public String toString() {
		return "Nurse [id=" + id + ", nurseNumber=" + nurseNumber + ", name=" + name + ", surname=" + surname + ", age="
				+ age + ", email=" + email + ", password=" + password + ", speciality=" + speciality + ", photo="
				+ Arrays.toString(photo) + "]";
	}

}
