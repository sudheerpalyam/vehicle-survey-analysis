package com.aconex.sudheer.vehiclesurvey.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.aconex.sudheer.vehiclesurvey.exception.NoFilesException;
import com.aconex.sudheer.vehiclesurvey.model.VehicleHit;

/**
 * @author Sudheer Palyam 06/05/2015
 */
public class FileLoader {
	Constants appConstants;

	public static void main(String[] args) throws IOException {
		// testLines();
		/*
		 * testReader(); testWriter(); testReadWriteLines(); testReaderLines();
		 */
	}

	/**
	 * Loop through each of the data files, get each vehicle hit record from
	 * file, parse it, construct VechicleHit Objects
	 * 
	 * @param filesList
	 * @return List of VehicleHit Objects
	 * @throws IOException
	 */
	public List<VehicleHit> getSensorsData(List<String> filesList) throws IOException {
		Path path = Paths.get(filesList.get(1)); // Get the first file.
		// TODO: Apply for all Data Files

		TimeUtil timeUtil = TimeUtil.getInstance();
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			List<VehicleHit> vehicleHits = reader.lines().map(line -> {
				VehicleHit dp = new VehicleHit();
				dp.setData(line);
				long recordedTimeMills = Long.parseLong(line.substring(1));
				dp.setRecordedTimeMills(recordedTimeMills);
				long epochTimeMills = timeUtil.previousMondayStart + recordedTimeMills;
				dp.setEpochTimeMills(epochTimeMills);
				dp.setBound(line.charAt(0));
				return dp;
			}).collect(Collectors.toList());
			return vehicleHits;
		}
	}

	/**
	 * Method of loop through each VehicleHit entry, compare it with previous
	 * Hit to determine whether it belongs to same day or next day. This is not
	 * possible to achieve this with Java 8 Stream, as Stream is once-off,
	 * parallel and does not provide access to previous element while Streaming.
	 */
	public void determineAndSetDayOfHit(List<VehicleHit> vehicleHitList) {
		int dayIncrement = 1;
		ListIterator<VehicleHit> it = vehicleHitList.listIterator();

		while (it.hasNext()) {
			if (it.hasPrevious()) {
				VehicleHit prevHit = it.previous();
				it.next(); // Since we moved cursor back one step
				VehicleHit currentHit = it.next();
				// Compare Current with previous hit, if previous time is
				// greater than current, then its next day!
				if (currentHit.getRecordedTimeMills() < prevHit.getRecordedTimeMills()) {
					++dayIncrement;
				}
				currentHit.setDay(dayIncrement);
			} else {
				// For first element in the list, where there is no previous
				it.next().setDay(dayIncrement); 
			}
		}
	}

	/**
	 * Look for vehicle sensor data in .txt file with in the project hierarchy
	 * 
	 * @throws IOException
	 */
	public List<String> getSensorsDataFiles() throws IOException {
		Path start = Paths.get("");
		int maxDepth = 5; // Looking at folder nesting level 5
		List<String> filesList;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) -> String.valueOf(path).endsWith(".txt"))) {
			filesList = stream.map(String::valueOf).collect(Collectors.toList());

			if (filesList.isEmpty()) {
				// runtime exception, no data found to process
				throw new NoFilesException("No Data Files Available To Process.");
			}
			filesList.forEach(System.out::println);
		}
		return filesList;
	}

	protected static void testReadWriteLines() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("res/nashorn1.js"));
		lines.add("print('foobar');");
		Files.write(Paths.get("res", "nashorn1-modified.js"), lines);
	}
}