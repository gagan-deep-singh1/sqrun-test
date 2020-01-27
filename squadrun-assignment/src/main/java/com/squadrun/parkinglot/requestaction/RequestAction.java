package com.squadrun.parkinglot.requestaction;

import com.squadrun.parkinglot.constants.Constants;
import com.squadrun.parkinglot.exceptions.ErrorType;
import com.squadrun.parkinglot.exceptions.ParkingException;
import com.squadrun.parkinglot.parkinglotServiceComponent.ParkingService;
import com.squadrun.parkinglot.parkinglotServices.IParkingService;
import com.squadrun.parkinglot.peson.Customer;

public class RequestAction {

	IParkingService parkingService;

	public RequestAction(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

	public void process(String input) throws ParkingException {
		String[] inputs = input.split(" ");
		String action = inputs[0];
		int level = 1;
		if (action.equals(Constants.CREATE_PARKING)) {
			int cap = Integer.parseInt(inputs[1]);
			try {
				parkingService.createParkingLotLevel(level, cap);
			} catch (NumberFormatException e) {
				throw new ParkingException(ErrorType.INVALID_VALUE.getMessage().replace("{variable}", "capacity"));
			}
		} else if (action.equals(Constants.PARK)) {
			parkingService.park(level, new Customer(Integer.parseInt(inputs[3]), inputs[1]));
		} else if (action.equals(Constants.REG_NUMBER_FOR_DRIVER_OF_AGE)) {
			parkingService.getRegNumberForAge(level, Integer.parseInt(inputs[1]));
		} else if (action.equals(Constants.SLOTS_NUMBER_FOR_DRIVER_OF_AGE)) {
			parkingService.getSlotNumbersFromAge(level, Integer.parseInt(inputs[1]));
		} else if (action.equals(Constants.SLOTS_NUMBER_FOR_REGISTRATION_NUMBER)) {
			parkingService.getSlotNoFromRegistrationNo(level, inputs[1]);
		} else if (action.equals(Constants.UNPARK)) {
			try {
				parkingService.unpark(level, Integer.parseInt(inputs[1]));
			} catch (NumberFormatException e) {
				throw new ParkingException(ErrorType.INVALID_VALUE.getMessage().replace("{variable}", "capacity"));
			}
		}
	}
}
