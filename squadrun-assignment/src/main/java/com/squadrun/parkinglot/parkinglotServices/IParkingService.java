package com.squadrun.parkinglot.parkinglotServices;

import com.squadrun.parkinglot.exceptions.ParkingException;
import com.squadrun.parkinglot.peson.Customer;

public interface IParkingService {

	public void createParkingLotLevel(int level, int capacity);

	public int park(int level, Customer customer) throws ParkingException;

	public void unpark(int level, int slotNumber) throws ParkingException;

	public void getRegNumberForAge(int level, int age) throws ParkingException;

	public void getSlotNumbersFromAge(int level, int age) throws ParkingException;

	public int getSlotNoFromRegistrationNo(int level, String registrationNo) throws ParkingException;

}
