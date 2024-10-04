package com.example.Hospital.Hospital;

public class Nurse {

	private int idNurse;
	private String name;
	private String password;
	private int age;
	private String speciality;
	private  static int totalNurse;

	public Nurse(String name, String password,int age, String speciality) {
    this.idNurse = totalNurse + 1;
		this.name = name;
		this.password = password;
		this.age=age;
		this.speciality=speciality;
  }

	public void setSpeciality(String speciality) {
		this.speciality = speciality;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
  }
	public int getIdNurse() {
		return idNurse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}