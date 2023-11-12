import java.util.ArrayList;

/*
 * The OpenCSV library for reading and writing CSV files. 
 * This library is to handle the traffic data CSV file.
 * 
 */
public class SmartCitySimulation {
	
	static ArrayList <Vehicle> vehicles = new ArrayList <> ();
	static ArrayList <EnvironmentalMonitoring> environment = new ArrayList <> ();
	static ArrayList <SmartLighting> lux = new ArrayList <> ();
	 
    public static void main(String[] args) {
    	
//       simulation of the list of the vehicles       		 
    	 vehicles.add (new Vehicle ("cars", 5.00, 23.00)); // assigning the values as an example
    	 vehicles.add (new Vehicle ("trucks", 8.00, 16.00));  
    	 vehicles.add (new Vehicle ("motorcycles", 11.00, 13.00)); 
    	 
//       simulation of the 6hour timeframe report of the level of noise (in dB), Air Quality Index (AQI), and the temperature     	 
    	 environment.add(new EnvironmentalMonitoring (50, 100, 10)); 
    	 environment.add(new EnvironmentalMonitoring (60, 120, 14));
    	 environment.add(new EnvironmentalMonitoring (80, 135, 15));
    	 environment.add(new EnvironmentalMonitoring (90, 140, 15));
    	 environment.add(new EnvironmentalMonitoring (79, 138, 16));
    	 environment.add(new EnvironmentalMonitoring (80, 155, 16));

//       simulation of the 6hour timeframe brightness report during the daylight by the sensor data
    	 lux.add(new SmartLighting (80)); // assigning the current values as an exapmple
    	 lux.add(new SmartLighting (150));
    	 lux.add(new SmartLighting (500));
    	 lux.add(new SmartLighting (1500));
    	 lux.add(new SmartLighting (5000));
    	 lux.add(new SmartLighting (5500));

    	EnvironmentalMonitoring environmentalMonitoring = new EnvironmentalMonitoring();
        TrafficManagement trafficManagement = new TrafficManagement();
        SmartLighting smartLighting = new SmartLighting(); 
           

        // Start simulation components using multithreading
        Thread trafficThread = new Thread(trafficManagement);
        Thread lightingThread = new Thread(smartLighting);
        Thread monitoringThread = new Thread(environmentalMonitoring);

        trafficThread.start();
        try {
            trafficThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();}
        
        lightingThread.start();
        try {
            lightingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();}
        
        monitoringThread.start();
        try {
            monitoringThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();}  
    }
}
