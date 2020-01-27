package com.squadrun.parkinglot.daocomponent;

import java.util.*;

import com.squadrun.parkinglot.constants.Constants;
import com.squadrun.parkinglot.dao.IParkingLevel;
import com.squadrun.parkinglot.peson.Customer;

public class ParkingLevel implements IParkingLevel {

	private int capacity;
	TreeSet<Integer> freeSlots;
	HashMap<Integer, Customer> slotMap;

	private static ParkingLevel parkingLevel = null;

	public static ParkingLevel getInstance(int level, int capacity) {
		if (parkingLevel == null) {
			synchronized (ParkingLevel.class) {
				if (parkingLevel == null) {
					parkingLevel = new ParkingLevel(level, capacity);
				}
			}
		}
		return parkingLevel;
	}

	private ParkingLevel(int level, int capacity) {
		this.capacity = capacity;
		this.freeSlots = new TreeSet<Integer>();
		for (int i = 1; i <= capacity; i++) {
			freeSlots.add(i);
		}
		slotMap = new HashMap<Integer, Customer>();
		for (int i = 1; i <= capacity; i++) {
			slotMap.put(i, null);
		}
	}

	@Override
	public int parkCar(Customer customer) {

		if (freeSlots.size() == 0) {
			return Constants.NOT_AVAILABLE;
		}
		if (slotMap.containsValue(customer)) {
			return Constants.VEHICLE_ALREADY_PRESENT;
		}
		int availabeSlot = freeSlots.first();
		slotMap.put(availabeSlot, customer);
		freeSlots.remove(availabeSlot);
		return availabeSlot;
	}

	@Override
	public Customer unpark(int slotNumber) {

		if (slotMap.get(slotNumber) == null) {
			return null;
		}
		Customer c = slotMap.get(slotNumber);
		slotMap.put(slotNumber, null);
		freeSlots.add(slotNumber);
		return c;
	}

	@Override
	public List<String> getRegNumberForAge(int age) {
		List<String> regNumbers = new ArrayList<String>();
		for (int i = 1; i <= capacity; i++) {
			Customer c = slotMap.get(i);
			if (c != null && c.getAge() == age) {
				regNumbers.add(c.getRegistrationNumber());
			}
		}
		return regNumbers;
	}

	@Override
	public List<Integer> getSlotNumbersFromAge(int age) {
		List<Integer> slotNumbers = new ArrayList<Integer>();
		for (int i = 1; i <= capacity; i++) {
			Customer c = slotMap.get(i);
			if (c != null && c.getAge() == age) {
				slotNumbers.add(i);
			}
		}

		return slotNumbers;
	}

	@Override
	public int getSlotNoFromRegistrationNo(String registrationNo) {
		for (int i = 1; i <= capacity; i++) {
			Customer c = slotMap.get(i);
			if (c != null && c.getRegistrationNumber().equals(registrationNo)) {
				return i;
			}
		}
		return -1;
	}

}
