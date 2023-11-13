import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Simulates smart lighting in the city, where street lights adjust their brightness 
 * based on the time of day and sensor data. It will demonstrate time handling.
 * 
 * The natural light source is the sun. Examples of common light levels:
 * Daylight: 10,000 Lux
 * Twilight: 10 Lux
 *  https://bioslighting.com/how-to-measure-light-intensity
 */

public class SmartLighting extends SmartCitySimulation implements Runnable {

	int brightness;
	private static String info;
	
	public SmartLighting(int brightness) {
		super();
		this.brightness = brightness;

	}

	public SmartLighting() {
	}

	public int getBrightness() {
		return brightness;
	}

	@Override
	public String toString() {
		return "The result of the sensor: [brightness = " + brightness + " Lux]";
	}

//  simulation of the 6hour report of the brightness sensor during a daytime    	 

	public void run() {

		try {
			for (int i = 0; i < 6; i++) { // the 6 hours of Monitoring. The every hour report (we use one minute for the
											// demonstation)

				if (((ArrayList<SmartLighting>) lux).get(i).getBrightness() < 200) {
					System.out.println("\nThe hour " + i + ": The lights should be turn on!");
				}

				if (((ArrayList<SmartLighting>) lux).get(i).getBrightness() > 200) {
					System.out.println("\nThe hour " + i + ": The lights are off.");
				}

				writing();

				Thread.sleep(1000);// 'sleep' method pauses the execution of the incrementation above inside the
									// method
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public String info(String info) { // the method 'info' saves the data of the ArrayList into String

		StringBuffer sb = new StringBuffer();

		for (SmartLighting n : SmartCitySimulation.lux) {
			sb.append(n);
			sb.append("\n ");
			info = sb.toString();
		}
		;
		return info;
	}

	public void writing() { // the method 'writing' writes the String 'info' into the file

		try {
			FileOutputStream file = new FileOutputStream("//home//dci-student//eclipse-workspace//SmartCitySimulation//resources//lightSensor_data.csv");
			String text = info(info);
			byte b[] = text.getBytes(); // converting the String text into the bytes;
			file.write(b); // writes the whole text onto the file
			file.close();

			System.out.println("The data has been successfully written into the file: 'lightSensor_data.csv'");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void reading(String fileName) throws IOException { // the method is only for one file due to the one
																		// link to the file

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("//home//dci-student//eclipse-workspace//SmartCitySimulation//resources//lightSensor_data.csv");

			int currentByte;
			while ((currentByte = fileInputStream.read()) != -1) {
				System.out.print((char) currentByte);
			} // iterate and read the whole file
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			} // close the method 'read'
		}
	}
}
