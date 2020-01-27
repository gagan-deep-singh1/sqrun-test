package com.squadrun.parkinglot.exceptions;

public class ParkingException extends Exception {

	private static final long serialVersionUID = 1993533645526403140L;
	private String errorType = null;

	public ParkingException(String message) {
		super(message);
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

}
