package com.squadrun.parkinglot.parkinglotServiceComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import com.squadrun.parkinglot.constants.Constants;
import com.squadrun.parkinglot.dao.IParkingLot;
import com.squadrun.parkinglot.daocomponent.ParkingLot;
import com.squadrun.parkinglot.exceptions.ErrorType;
import com.squadrun.parkinglot.exceptions.ParkingException;
import com.squadrun.parkinglot.parkinglotServices.IParkingService;
import com.squadrun.parkinglot.peson.Customer;

public class ParkingService implements IParkingService {

	private IParkingLot parkingLot = null;

	@Override
	public void createParkingLotLevel(int level, int capacity) {
		if (parkingLot != null) {
			System.out.println("Parking exist already");
			return;
		}
		ArrayList<Integer> levelList = new ArrayList<Integer>();
		ArrayList<Integer> capacityList = new ArrayList<Integer>();
		levelList.add(level);
		capacityList.add(capacity);
		this.parkingLot = ParkingLot.getInstance(levelList, capacityList);
		System.out.println("Created Parking of " + capacity + " slots");
	}

	@Override
	public int park(int level, Customer customer) throws ParkingException {
		try {
			int result = parkingLot.getPark(level, customer);
			if (result == Constants.NOT_AVAILABLE) {
				System.out.println("Space not available");
				return result;
			}
			if (result == Constants.VEHICLE_ALREADY_PRESENT) {
				System.out.println("Vehicle aready present in parking");
				return result;
			}
			System.out.println("Car with vehicle registration number " + customer.getRegistrationNumber()
					+ " has been parked at slot number " + result);

			return 0;
		} catch (Exception e) {
			throw new ParkingException(ErrorType.PROCESS_ERROR.getMessage());
		}
	}

	@Override
	public void unpark(int level, int slotNumber) throws ParkingException {
		try {
			Customer c = parkingLot.unpark(level, slotNumber);
			if (c != null) {
				System.out.println("Slot number " + slotNumber + " vacated, the car with vehicle registration number "
						+ c.getRegistrationNumber() + " left the space, the driver of the car was of age "
						+ c.getAge());

			} else {
				System.out.println("Already Unoccupied");
			}
		} catch (Exception e) {
			throw new ParkingException(ErrorType.INVALID_VALUE.getMessage().replace("{variable}", "slot number"));
		}
	}

	@Override
	public void getRegNumberForAge(int level, int age) throws ParkingException {
		try {
			List<String> registrationList = parkingLot.getRegNumberForAge(level, age);
			if (registrationList.size() == 0)
				System.out.println("null");
			else
				System.out.println(String.join(",", registrationList));
		} catch (Exception e) {
			throw new ParkingException(ErrorType.PROCESS_ERROR.getMessage());
		}
	}

	@Override
	public void getSlotNumbersFromAge(int level, int age) throws ParkingException {
		try {
			List<Integer> slotList = parkingLot.getSlotNumbersFromAge(level, age);
			if (slotList.size() == 0)
				System.out.println("Not Found");
			StringJoiner joiner = new StringJoiner(",");
			for (Integer slot : slotList) {
				joiner.add(slot + "");
			}
			System.out.println(joiner.toString());
		} catch (Exception e) {
			throw new ParkingException(ErrorType.PROCESS_ERROR.getMessage());
		}
	}

	@Override
	public int getSlotNoFromRegistrationNo(int level, String registrationNo) throws ParkingException {
		try {
			int value = parkingLot.getSlotNoFromRegistrationNo(level, registrationNo);
			System.out.println(value != -1 ? value : "Not Found");
			return value;
		} catch (Exception e) {
			throw new ParkingException(ErrorType.PROCESS_ERROR.getMessage());
		}
	}

}
