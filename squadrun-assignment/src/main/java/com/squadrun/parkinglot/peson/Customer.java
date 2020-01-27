package com.squadrun.parkinglot.peson;

public class Customer {
	private String registrationNumber;
	private int age;

	public Customer(int age, String registrationNumber) {
		this.age = age;
		this.registrationNumber = registrationNumber;
	}

	public int getAge() {
		return age;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}
}
