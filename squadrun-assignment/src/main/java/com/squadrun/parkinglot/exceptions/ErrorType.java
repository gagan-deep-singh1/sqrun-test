package com.squadrun.parkinglot.exceptions;

public enum ErrorType {

	PARKING_ALREADY_EXIST("Sorry Parking Already Created, It can not be recreated."),
	PARKING_DOES_NOT_EXIST_ERROR("Sorry, Car Parking Does not Exist"),
	INVALID_VALUE("{variable} value is incorrect"),
	INVALID_FILE("Invalid File"),
	INVALID_REQUEST("Invalid Request"),
	PROCESS_ERROR("Processing Error");
	

	private String message = "";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private ErrorType(String message) {
		this.message = message;
	}

}
