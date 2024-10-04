package com.example.Hospital.Hospital;

public class Nurse {

	private String name;
	private int age;
	private String password;
	private String speciality;
	private int idNurse;
	private static int totalNurse;

	public Nurse(String name, int age, String password, String speciality) {

		this.name = name;
		this.age = age;
		this.password = password;
		this.speciality = speciality;
		this.idNurse = totalNurse + 1;
		totalNurse++;
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

	public int getIdNurse() {
		return idNurse;
	}

	@Override
	public String toString() {
		return "Nurse{" + "Name='" + name + '\'' + ", Age=" + age + '\'' + ", idNurse='" + idNurse + '\''
				+ ", speciality='" + speciality + '\'' + '}';

	}

}
