package Filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import kml.kml;
import src.ScannerFunctions;

public class Filters {
	
	/**
	 * 
	 * @param csvFile
	 * @param pathToWriteKML
	 * @param startTime
	 * @param endTime
	 */
	public static void filtercsvFileByTime(String csvFile, String pathToWriteKML, String startTime, String endTime ){
		int lineYear, lineMonth, lineDay;
		int startYear, startMonth, startDay;
		int endYear, endMonth, endDay;
		startYear = Integer.parseInt(startTime.substring(0, 4));
		startMonth = Integer.parseInt(startTime.substring(5, 7));
		startDay = Integer.parseInt(startTime.substring(8, 10));
		endYear = Integer.parseInt(endTime.substring(0, 4));
		endMonth = Integer.parseInt(endTime.substring(5, 7));
		endDay = Integer.parseInt(endTime.substring(8, 10));

		try {
			FileReader fr = new FileReader(csvFile);
			BufferedReader br= new BufferedReader(fr); 
			ArrayList<String> filteredCSV = new ArrayList<String>();
			String brFirstLine = br.readLine(); //skipping first line
			String brLine = br.readLine();
			String[] line = brLine.split(",");
			String timeColumn = line[0];
			String hmsStart = startTime.substring(11, timeColumn.length()); //hms = hour, minutes, seconds
			String hmsEnd = endTime.substring(11, timeColumn.length());
			String lineDate = "";
			LocalTime lineTime = null;

			// filtering the csv file
			while(brLine != null){
				//getting line's time data
				lineTime = LocalTime.parse(brLine.substring(11, timeColumn.length()));
				lineDate = brLine.substring(0, 10);
				lineYear = Integer.parseInt(lineDate.substring(0, 4));
				lineMonth = Integer.parseInt(lineDate.substring(5, 7));
				lineDay = Integer.parseInt(lineDate.substring(8, 10));
				if(lineYear>=startYear && lineYear<=endYear && lineMonth>=startMonth && lineMonth<=endMonth && lineDay>startDay) {
					hmsStart = "00:00:00";
				}
				if(lineYear>=startYear && lineYear<=endYear && lineMonth>=startMonth && lineMonth<=endMonth && lineDay<endDay) {
					hmsEnd = "23:59:59";
				}
				// checking if the line's time is corresponding to the given time filter
				if(lineYear>=startYear && lineYear<=endYear && lineMonth>=startMonth && lineMonth<=endMonth && lineDay>=startDay && lineDay<=endDay) {
					if(lineTime.isAfter(LocalTime.parse(hmsStart)) && lineTime.isBefore(LocalTime.parse(hmsEnd)))
					{
						filteredCSV.add(brLine);
						brLine = br.readLine();
					}
					else brLine = br.readLine();
				}
				else brLine = br.readLine();
			}
			fr.close();
			br.close();

			ArrayList<String[]> filtered = new ArrayList<String[]>();
			for(int i = 0; i < filteredCSV.size(); i++)
			{
				filtered.add(filteredCSV.get(i).split(","));
			}
			kml.makeKmlFile(filtered, pathToWriteKML);
			//printToKML(filtered, pathToWriteKML); // creating the kml file
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void filtercsvFileByID(String csvFile, String pathToWriteKML, String ID) {

		try {
			FileReader fr = new FileReader(csvFile);
			BufferedReader br= new BufferedReader(fr); 
			ArrayList<String> filteredCSV = new ArrayList<String>();
			String brFirstLine = br.readLine(); // skipping first line
			String brLine = br.readLine();

			// filtering the csv file
			while(brLine != null){
				String[] line = brLine.split(",");
				if(line[1].equals(ID))
				{
					filteredCSV.add(brLine);
					brLine = br.readLine();
				}
				else brLine = br.readLine();
			}

			fr.close();
			br.close();

			ArrayList<String[]> filtered = new ArrayList<String[]>();
			for(int i = 0; i < filteredCSV.size(); i++)
			{
				filtered.add(filteredCSV.get(i).split(","));
			}

			kml.printToKML(filtered, pathToWriteKML);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}



	public static void filtercsvFileByGPS(String csvFile, String pathToWriteKML, double lonStart, double latStart, double lonEnd, double latEnd){
		// filter rectangle coordinates
		double lineLon, lineLat;
		double xTopRight = Math.max(lonStart, lonEnd);
		double yTopRight = Math.max(latStart, latEnd);
		double xBottomLeft = Math.min(lonStart, lonEnd);
		double yBottomLeft = Math.min(latStart, latEnd);
		double xBottomRight = xTopRight;
		double yBottomRight = yBottomLeft;
		double xTopLeft = xBottomLeft;
		double yTopLeft = yTopRight;

		try {

			FileReader fr = new FileReader(csvFile);
			BufferedReader br= new BufferedReader(fr); 
			ArrayList<String> filteredCSV = new ArrayList<String>();
			String brFirstLine = br.readLine(); // skipping first line
			String brLine = br.readLine();

			// filtering the csv file
			while(brLine != null){
				String[] line = brLine.split(",");
				lineLat = Double.parseDouble(line[2]);
				lineLon= Double.parseDouble(line[3]);
				if(lineLat<yTopRight && lineLat>=yBottomRight && lineLon<xTopRight && lineLon>=xTopLeft)
				{
					filteredCSV.add(brLine);
					brLine = br.readLine();
				}
				else brLine = br.readLine();
			}

			fr.close();
			br.close();

			ArrayList<String[]> filtered = new ArrayList<String[]>();
			for(int i = 0; i < filteredCSV.size(); i++)
			{
				filtered.add(filteredCSV.get(i).split(","));
			}

			double[] rectTop = {xTopLeft, yTopLeft, xTopRight, yTopRight}; 
			double[] rectBot = {xBottomLeft, yBottomLeft, xBottomRight, yBottomRight};

			ScannerFunctions.printToKmlByGPS(filtered, pathToWriteKML, rectTop, rectBot);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}



}
