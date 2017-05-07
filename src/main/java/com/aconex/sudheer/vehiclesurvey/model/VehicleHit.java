package com.aconex.sudheer.vehiclesurvey.model;

import java.time.Instant;
import com.aconex.sudheer.vehiclesurvey.util.TimeUtil;

/**
 * @author Sudheer Palyam 06/05/2015
 * 
 *         POJO for each recorded entry
 */
public class VehicleHit {
	String data;
	long recordedTimeMills;
	long epochTimeMills;
	char bound;
	String timeSinceMidNight;
	int day;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getRecordedTimeMills() {
		return recordedTimeMills;
	}

	public void setRecordedTimeMills(long recordedTimeMills) {
		this.recordedTimeMills = recordedTimeMills;
	}

	public long getEpochTimeMills() {
		return epochTimeMills;
	}

	public void setEpochTimeMills(long epochTimeMills) {
		if (epochTimeMills <= 0) {
		      throw new IllegalArgumentException("Vehicle Hit Time cannot be Negative or Zero");
		    }
		this.epochTimeMills = epochTimeMills;
	}

	public char getBound() {
		return bound;
	}

	public void setBound(char bound) {
		this.bound = bound;
	}

	public String getTimeSinceMidNight() {
		return timeSinceMidNight;
	}

	public void setTimeSinceMidNight(String timeSinceMidNight) {
		this.timeSinceMidNight = timeSinceMidNight;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VehicleHit))
			return false;
		VehicleHit vh = (VehicleHit) obj;
		return vh.getData().equals(this.getData());
	}

	@Override
	public String toString() {

		return "VehicleHit [data=" + data + ", recordedTimeMills=" + recordedTimeMills + ", epochTimeMills=" + epochTimeMills + ", epochTime="
				+ TimeUtil.df.format(Instant.ofEpochMilli(epochTimeMills)) + ", day=" + day + ", bound=" + bound + ", timeSinceMidNight=" + timeSinceMidNight
				+ "]";
	}
}
