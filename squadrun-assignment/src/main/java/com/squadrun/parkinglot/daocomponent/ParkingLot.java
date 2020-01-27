package com.squadrun.parkinglot.daocomponent;

import java.util.HashMap;
import java.util.List;

import com.squadrun.parkinglot.dao.IParkingLevel;
import com.squadrun.parkinglot.dao.IParkingLot;
import com.squadrun.parkinglot.peson.Customer;

public class ParkingLot implements IParkingLot {

	private HashMap<Integer, IParkingLevel> levelMap;

	private static ParkingLot parkingLot = null;

	public static ParkingLot getInstance(List<Integer> parkingLevels, List<Integer> capacityList) {
		if (parkingLot == null) {
			synchronized (ParkingLot.class) {
				if (parkingLot == null) {
					parkingLot = new ParkingLot(parkingLevels, capacityList);
				}
			}
		}
		return parkingLot;
	}

	private ParkingLot(List<Integer> parkingLevels, List<Integer> capacityList) {
		if (levelMap == null) {
			levelMap = new HashMap<>();
		}
		for (int i = 0; i < parkingLevels.size(); i++) {
			levelMap.put(parkingLevels.get(i), ParkingLevel.getInstance(parkingLevels.get(i), capacityList.get(i)));
		}

	}

	@Override
	public int getPark(int level, Customer customer) {
		return levelMap.get(level).parkCar(customer);
	}

	@Override
	public Customer unpark(int level, int slotNumber) {
		return levelMap.get(level).unpark(slotNumber);
	}

	@Override
	public List<String> getRegNumberForAge(int level, int age) {
		return levelMap.get(level).getRegNumberForAge(age);
	}

	@Override
	public List<Integer> getSlotNumbersFromAge(int level, int age) {
		return levelMap.get(level).getSlotNumbersFromAge(age);
	}

	@Override
	public int getSlotNoFromRegistrationNo(int level, String registrationNo) {
		return levelMap.get(level).getSlotNoFromRegistrationNo(registrationNo);
	}

}
