/*
 * Simulates environmental monitoring, including air quality, 
 * noise levels, and temperature. It will demonstrate loops and time handling.
 * 
 * Noise above 70 dB over a prolonged period of time may start to damage your hearing. 
 * Loud noise above 120 dB can cause immediate harm to your ears. 
 * City traffic: max 80–85, e.g. Motorcycle: 95
 *                  
 * Air quality is reported using the Air Quality Index (AQI). 
 * The AQI has six categories that communicate the level of health, from 0 - 500.
 * The AQI starting from 150 is considered as Unhealthy: https://scijinks.gov/air-quality/   
 * 
 * Generally, it's best to avoid starting your car in temperatures below -20°C (-4°F). 
 * The colder it is outside, the harder it is for your car's engine and battery to work properly. 
 * Cold weather can also cause damage to the rubber seals and gaskets in your car.
 * 
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class EnvironmentalMonitoring extends SmartCitySimulation implements Runnable {

	int noiseLevelLimit; // see above
	int airQualityIndexLimit; // see above
	int temperatureLimit; // see above
	private String info;
	private static String fileName = "environment_data.csv";

	public EnvironmentalMonitoring(int noiseLevelLimit, int airQualityIndexLimit, int temperatureLimit) {
		super();
		this.noiseLevelLimit = noiseLevelLimit;
		this.airQualityIndexLimit = airQualityIndexLimit;
		this.temperatureLimit = temperatureLimit;
	}

	public EnvironmentalMonitoring() {
		super();
	}

	public int getNoiseLevelLimit() {
		return noiseLevelLimit;
	}

	public int getAirQualityIndexLimit() {
		return airQualityIndexLimit;
	}

	public int getTemperatureLimit() {
		return temperatureLimit;
	}

	@Override
	public String toString() {
		return "Environmental Monitoring: The noise level: " + noiseLevelLimit + ", The Air Quality Index (AQI): "
				+ airQualityIndexLimit + ", The temperature: " + temperatureLimit;
	}

//  simulation of the 6hour report of the level of noise (in dB), Air Quality Index (AQI), and the temperature     	 

	public void run() { // Implement environmental monitoring logic / Monitor air quality, noise levels, temperature

		try {
			for (int i = 0; i < 6; i++) { // the 12 hours of Monitoring. The every hour report (we use one minute for
											// the demonstation)

				if (((ArrayList<EnvironmentalMonitoring>) environment).get(i).getNoiseLevelLimit() >= 90) {
					System.out.println("The hour " + i + ": Attention! The level of noise is too high.");
				}
				if (((ArrayList<EnvironmentalMonitoring>) environment).get(i).getAirQualityIndexLimit() >= 150) {
					System.out.println("The hour " + i + ": Attention! The Air Quality Index is too high.");
				}
				if (((ArrayList<EnvironmentalMonitoring>) environment).get(i).getTemperatureLimit() <= -20) {
					System.out.println("The hour " + i + ": Attention! The temperature is too low.");
				}
				if (((ArrayList<EnvironmentalMonitoring>) environment).get(i).getNoiseLevelLimit() < 90
						&& ((ArrayList<EnvironmentalMonitoring>) environment).get(i).getAirQualityIndexLimit() < 150
						&& ((ArrayList<EnvironmentalMonitoring>) environment).get(i).getTemperatureLimit() > -20)

				{
					System.out.println("\nThe hour " + i + ": all the Environmental conditions are acceptable.");
				}

				writing();

				Thread.sleep(1000);// 'sleep' method pauses the execution of the incrementation above inside the
									// method

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			reading(fileName); // call the method above to read the file;
			System.out.println("\nReading the file: 'environment_data.csv' was successful.");
		} // report a successfully reading;
		catch (IOException e) {
			System.err.println("An error occurred during the operation: " + e.getMessage());
		} // if Error;

	}

	public String info(String info) { // the method 'info' saves the data of the ArrayList into String

		StringBuffer sb = new StringBuffer();

		for (EnvironmentalMonitoring n : SmartCitySimulation.environment) {
			sb.append(n);
			sb.append("\n ");
			info = sb.toString();
		}
		;
		return info;
	}

	public void writing() { // the method 'writing' writes the String 'info' into the file

		try {
			FileOutputStream file = new FileOutputStream(
					"//home//dci-student//eclipse-workspace//SmartCitySimulation//resources//environment_data.csv");
			String text = info(info);
			byte b[] = text.getBytes(); // converting the String text into the bytes;
			file.write(b); // writes the whole text onto the file
			file.close();

			System.out.println("The data has been successfully written into the file: 'environment_data.csv'\n");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void reading(String fileName) throws IOException { // the method is only for one file due to the one
																		// link to the file

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(
					"//home//dci-student//eclipse-workspace//SmartCitySimulation//resources//environment_data.csv"); 
																														
			int currentByte;
			while ((currentByte = fileInputStream.read()) != -1) {
				System.out.print((char) currentByte);
			} // iterate and read the whole file
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			} // close the method 'read';
		}
	}
}
