package Phase3Implementation;

public class Vitals {
	// Class representing a set of vital signs for a User
	private double temperature;
	private int bloodPressure, weight, height;

	// Constructor to create a Vitals object with specific values  
	public Vitals(double temperature, int bloodPressure, int weight, int height) {
		this.temperature = temperature;
		this.bloodPressure = bloodPressure;
		this.height = height;
		this.weight = weight;
	}

	// Method to convert Vitals object to a comma-separated String  
	public String toData() {
		return temperature + "," + bloodPressure + "," + weight + "," + height;
	}
	
}
