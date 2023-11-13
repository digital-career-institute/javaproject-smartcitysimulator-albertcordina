import java.io.IOException;
import java.util.ArrayList;

/*
 * The OpenCSV library for reading and writing CSV files. 
 * This library is to handle the traffic data CSV file.
 * 
 */
public class SmartCitySimulation {

	static ArrayList<Vehicle> vehicles = new ArrayList<>();
	static ArrayList<EnvironmentalMonitoring> environment = new ArrayList<>();
	static ArrayList<SmartLighting> lux = new ArrayList<>();
	static String fileName;

	public static void main(String[] args) {

//       simulation of the list of the vehicles       		 
		vehicles.add(new Vehicle("cars", 5.00, 22.00)); // assigning the values as an example
		vehicles.add(new Vehicle("trucks", 8.00, 16.00));
		vehicles.add(new Vehicle("motorcycles", 11.00, 13.00));

//       simulation of the 6hour timeframe report of the level of noise (in dB), Air Quality Index (AQI), and the temperature     	 
		environment.add(new EnvironmentalMonitoring(51, 100, 10));
		environment.add(new EnvironmentalMonitoring(60, 120, 14));
		environment.add(new EnvironmentalMonitoring(80, 135, 15));
		environment.add(new EnvironmentalMonitoring(90, 140, 15));
		environment.add(new EnvironmentalMonitoring(79, 138, 16));
		environment.add(new EnvironmentalMonitoring(80, 155, 16));

//       simulation of the 6hour timeframe brightness report during the daylight by the sensor data
		lux.add(new SmartLighting(94)); // assigning the current values as an exapmple
		lux.add(new SmartLighting(150));
		lux.add(new SmartLighting(500));
		lux.add(new SmartLighting(1500));
		lux.add(new SmartLighting(5000));
		lux.add(new SmartLighting(5500));

		EnvironmentalMonitoring environmentalMonitoring = new EnvironmentalMonitoring();
		TrafficManagement trafficManagement = new TrafficManagement();
		SmartLighting smartLighting = new SmartLighting();

		// Start simulation components using multithreading
		Thread trafficThread = new Thread(trafficManagement);
		Thread lightingThread = new Thread(smartLighting);
		Thread monitoringThread = new Thread(environmentalMonitoring);

		trafficThread.start();
		lightingThread.start();
		monitoringThread.start();

		try {
			trafficThread.join();
			lightingThread.join();
			monitoringThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Reading data from CSV files:
		try {
			smartLighting.reading(fileName); // call the method to read the file
			System.out.println("Reading the file: 'lightSensor_data.csv' was successful.\n");
		} // report a successfully reading
		catch (IOException e) {
			System.err.println("An error occurred during the operation: " + e.getMessage());
		} // if Error

		try {
			environmentalMonitoring.reading(fileName); // call the method to read the file
			System.out.println("Reading the file: 'environment_data.csv' was successful.\n");
		} // report a successfully reading
		catch (IOException e) {
			System.err.println("An error occurred during the operation: " + e.getMessage());
		} // if Error;

		try {
			trafficManagement.reading(fileName); // call the method to read the file
			System.out.println("Reading the file: 'traffic_data.csv' was successful.\n");
		} // report a successfully reading
		catch (IOException e) {
			System.err.println("An error occurred during the operation: " + e.getMessage());
		} // if Error
	}
}
