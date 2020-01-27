package com.squadrun.parkinglot.dao;

import java.util.List;

import com.squadrun.parkinglot.peson.Customer;

public interface IParkingLevel {
	public int parkCar(Customer customer);

	public Customer unpark(int slotNumber);

	public List<String> getRegNumberForAge(int age);

	public List<Integer> getSlotNumbersFromAge(int age);

	public int getSlotNoFromRegistrationNo(String registrationNo);
}
