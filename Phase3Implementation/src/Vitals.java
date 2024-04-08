package Phase3Implementation;

public class Vitals {
	private double temperature;
	private int bloodPressure, weight, height;
	
	public Vitals(double temperature, int bloodPressure, int weight, int height) {
		this.temperature = temperature;
		this.bloodPressure = bloodPressure;
		this.height = height;
		this.weight = weight;
	}
	
	public String toData() {
		return temperature + "," + bloodPressure + "," + weight + "," + height;
	}
	
}