package com.squadrun.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.squadrun.parkinglot.exceptions.ErrorType;
import com.squadrun.parkinglot.exceptions.ParkingException;
import com.squadrun.parkinglot.parkinglotServiceComponent.ParkingService;
import com.squadrun.parkinglot.requestaction.RequestAction;

public class Main {
	public static void main(String[] args) throws Exception {

		System.out.println("------------Parking Lot--------");
		RequestAction request = new RequestAction(new ParkingService());
		File file = new File(args[0]);
		BufferedReader reader = null;
		if (!file.canRead())
			file.setReadable(true);
		try {
			reader = new BufferedReader(new FileReader(file));
			String input = reader.readLine();
			while (input != null) {
				input = input.trim();
				try {
					request.process(input);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				input = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			throw new ParkingException(ErrorType.INVALID_FILE.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
			}
		}

	}

}
