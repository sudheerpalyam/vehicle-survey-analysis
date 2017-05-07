package com.aconex.sudheer.vehiclesurvey.util;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class TimeUtil {

	private static TimeUtil instance;

	// Hide the constructor
	private TimeUtil() {
	}

	// Create Singleton Object using Double Locking Mechanism
	public static TimeUtil getInstance() {
		if (instance == null) {
			synchronized (TimeUtil.class) {
				if (instance == null) {
					instance = new TimeUtil();
					instance.initialize();
				}
			}
		}
		return instance;
	}

	static ZoneId zoneId = ZoneId.systemDefault(); // ZoneId.of( "America/Montreal" )
	public static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS").withZone(zoneId);

	long previousMondayStart;

	// Initialize application wide preliminary objects
	private void initialize() {

		// Get the current week's Monday in java 8 java.time.LocalDate format
		LocalDate previousMonday = LocalDate.now(zoneId).with(TemporalAdjusters.previous(DayOfWeek.MONDAY));

		// Get the current week's Monday's start time (Mid Night)
        Instant mondayStartInstant = previousMonday.atStartOfDay(zoneId).toInstant();
		this.previousMondayStart = mondayStartInstant.toEpochMilli();
	}
}
