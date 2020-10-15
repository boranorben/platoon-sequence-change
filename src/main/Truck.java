package main;

public class Truck {
	private String order;
	private double currentFuel;
	private double fuelConsump = 0.326; // 32.6 LITERS/100KM

	private final double FUEL_INIT = 1135.82; // 300 GALLON = 1135.82 LITERS

	public Truck(String order) {
		this.order = order;
		this.currentFuel = FUEL_INIT;
	}

	public String getOrder() {
		return this.order;
	}

	public double getCurrentFuel() {
		return this.currentFuel;
	}

	public double getFuelConsump() {
		return this.fuelConsump;
	}

	protected void fuelCalculate(double orderReduced) {
		this.currentFuel = this.currentFuel - this.fuelConsump + (this.fuelConsump * orderReduced / 100);
	}
}
