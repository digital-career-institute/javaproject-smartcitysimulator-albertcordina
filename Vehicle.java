public class Vehicle {
	
	String typeOfVehicle;
	double timeFrom;
	double timeTo;
	
	public Vehicle (String typeOfVehicle, double timeFrom, double timeTo) {
		
		this.typeOfVehicle = typeOfVehicle;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
	}

	public String getTypeOfVehicle() {
		return typeOfVehicle;
	}

	public double getTimeFrom() {
		return timeFrom;
	}

	public double getTimeTo() {
		return timeTo;
	}

	@Override
	public String toString() {
		return "The type of the vehicle: " + typeOfVehicle + ", the allowed driving period from " + timeFrom + " to " + timeTo;
	}
}
