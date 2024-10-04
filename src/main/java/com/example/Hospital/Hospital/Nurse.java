package com.example.Hospital.Hospital;

public class Nurse {
	private String name;
	private String password;
	private int age;
	private String speciality;
	private int idNurse;
	private  static int totalNurse;

	public Nurse(String name, String password,int edad, String profesionalidad, int idNurse) {
		this.name = name;
		this.password = password;
		this.age=edad;
		this.speciality=profesionalidad;
		this.idNurse=idNurse;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}