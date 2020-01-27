package com.squadrun.parkinglot.dao;

import java.util.List;

import com.squadrun.parkinglot.peson.Customer;

public interface IParkingLot {

	public int getPark(int level, Customer customer);

	public Customer unpark(int level, int slotNumber);

	public List<String> getRegNumberForAge(int level, int age);

	public List<Integer> getSlotNumbersFromAge(int level, int age);

	public int getSlotNoFromRegistrationNo(int level, String registrationNo);

}
