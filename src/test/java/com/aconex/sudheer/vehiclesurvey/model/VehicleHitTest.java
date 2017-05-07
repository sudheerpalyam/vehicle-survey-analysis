/**
 * @author Sudheer Palyam 06/05/2015
 * Unit Test for com.aconex.sudheer.vehiclesurvey.model.VehicleHit.java
 */
package com.aconex.sudheer.vehiclesurvey.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author sudheer
 *
 */
public class VehicleHitTest {

	private VehicleHit vehicleHit;
	
	 @BeforeEach
	  void init() {
		 vehicleHit = new VehicleHit();
	  }

	  @DisplayName("Test VehicleHit with valid data")
	  @Test
	  void testVehicleHit() {
		  
	    String hitData = "A98186";
	    vehicleHit.setData(hitData);
	    vehicleHit.setRecordedTimeMills(98186);

	    assertEquals(hitData, vehicleHit.getData());
	    assertEquals(98186, vehicleHit.getRecordedTimeMills());
	  }

	  @DisplayName("Test VehicleHit with invalid data")
	  @Test
	  void testVehicleHitWithInValidData() {

	    long mills = -10;
	    assertThrows(IllegalArgumentException.class, () -> {
	    	vehicleHit.setEpochTimeMills(mills);
	    });

	  }

	  @AfterEach
	  void tearDown() {
		  vehicleHit = null;
	  }

}
