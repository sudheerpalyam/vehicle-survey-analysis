package com.aconex.sudheer.vehiclesurvey;

import java.util.List;

import com.aconex.sudheer.vehiclesurvey.model.VehicleHit;
import com.aconex.sudheer.vehiclesurvey.util.FileLoader;

/**
 * @author Sudheer Palyam 06/05/2015
 * 
 *         Entry point Main Application, to analyze Vehicle Survey Data
 */
public class Application {

	private static Application instance;

	// Hide the constructor
	private Application() {
	}

	// Create Singleton Object using Double Locking Mechanism
	public static Application getInstance() {
		if (instance == null) {
			synchronized (Application.class) {
				if (instance == null) {
					instance = new Application();
					instance.initialize();
				}
			}
		}
		return instance;
	}

	// Initialize application wide preliminary objects
	private void initialize() {

	}

	private void perform() throws Exception {
		FileLoader self = new FileLoader();
		
		// Get Data Files
		List<String> filesList = self.getSensorsDataFiles();
		
		// Parse Data files and get list of VehicleHit Objects
		List<VehicleHit> vehicleHitList = self.getSensorsData(filesList);
		
		// Classify VehichleHits by day it occurred
		self.determineAndSetDayOfHit(vehicleHitList);
		
		// Just Print
		vehicleHitList.forEach(System.out::println);

	}

	public static void main(String[] args) {
		try {

			// Initialize and set the application context
			Application app = Application.getInstance();

			// Perform business logic
			app.perform();

		} catch (Exception e) {
			System.out.println(
					"Something is not correct. Application cannot proceed. Please check stack trace for details.");
			e.printStackTrace();
		}
	}
}
