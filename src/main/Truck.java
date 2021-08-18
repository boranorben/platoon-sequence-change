package main;

public class Truck implements Comparable<Truck> {
	private String order;
	private double currentFuel;
	protected final double FUEL_CONSUMP = 0.326; // 32.6 l/100km
	protected final double FUEL_INIT = 1135.62; // 300 gallon = 1135.62 liters

	public Truck(String order, Double initFuel) {
		this.order = order;
		if (initFuel == null) {
			this.currentFuel = FUEL_INIT;
		} else {
			this.currentFuel = initFuel;
		}
	}

	public String getOrder() {
		return this.order;
	}

	public double getCurrentFuel() {
		return this.currentFuel;
	}

	protected void fuelCal(double orderReduce) {
		this.currentFuel += (this.FUEL_CONSUMP * orderReduce / 100) - this.FUEL_CONSUMP;
	}

	@Override
	public int compareTo(Truck o) {
		return this.currentFuel < o.currentFuel ? 1 : -1;
	}
}
