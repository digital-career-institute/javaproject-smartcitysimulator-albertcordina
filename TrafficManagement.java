import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
/*
 * Simulates smart traffic management, including traffic lights, congestion detection, 
 * and rerouting of vehicles.
 *
 * It will demonstrate collections and input/output for reading data from a CSV file.
 * 
 */
import java.util.List;

public class TrafficManagement extends SmartCitySimulation implements Runnable {

	private static String info;
	private static String fileName = "traffic_data.csv";

	 // Read traffic data from CSV
     // Implement traffic management logic using collections
     // Handle exceptions for I/O operations
	
 public void run() {
	 
	 writing(); // calling the method of writing into CSV file
     try {
         reading(fileName);  // call the method above;
         System.out.println("\nReading the file: 'traffic_data.csv' was successful.");}  //  report a successfully reading
     catch (IOException e) { System.err.println("An error occurred during the operation: " + e.getMessage());} // if Error
 }
 
 public String info (String info) { // the method 'info' saves the data of the ArrayList into String
	 
	 StringBuffer sb = new StringBuffer();
	 
	 for (Vehicle n : SmartCitySimulation.vehicles) {	 
		 sb.append(n);
		 sb.append("\n ");
		 info = sb.toString(); };
	 return info;
 }
 
 public void writing () { // the method 'writing' writes the String 'info' into the file
 
  try {
        FileOutputStream file = new FileOutputStream("//home//dci-student//eclipse-workspace//SmartCitySimulation//resources//traffic_data.csv");
        String text = info(info); 
        byte b[] = text.getBytes(); // converting the String text into the bytes;
        file.write(b); // writes the whole text onto the file
        file.close();

        System.out.println("The data has been successfully written into the file: 'traffic_data.csv'");
    } catch (Exception e) { System.out.println(e);}
  }

 public static void reading (String fileName) throws IOException { // the method is only for one file due to the one link to the file

     FileInputStream fileInputStream = null;
     try {
         fileInputStream = new FileInputStream("//home//dci-student//eclipse-workspace//SmartCitySimulation//resources//traffic_data.csv"); // new object to link to the file
         int currentByte;
         while ((currentByte = fileInputStream.read()) != -1) { System.out.print((char) currentByte);} // iterate and read the whole file
     }
     finally {
         if (fileInputStream != null) { fileInputStream.close();} // close the method 'read'
     }
   }
}
