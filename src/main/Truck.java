package main;

<<<<<<< HEAD
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
=======
public class Truck {
	private String order;
	private double currentFuel;
	private double fuelConsump = 0.326; // 32.6 LITERS/100KM

	private final double FUEL_INIT = 1135.82; // 300 GALLON = 1135.82 LITERS

	public Truck(String order) {
		this.order = order;
		this.currentFuel = FUEL_INIT;
>>>>>>> 77efec613a243525b743ff1c7533bf1e2a022864
	}

	public String getOrder() {
		return this.order;
	}

	public double getCurrentFuel() {
		return this.currentFuel;
	}

<<<<<<< HEAD
	protected void fuelCal(double orderReduce) {
		this.currentFuel += (this.FUEL_CONSUMP * orderReduce / 100) - this.FUEL_CONSUMP;
	}

	@Override
	public int compareTo(Truck o) {
		return this.currentFuel < o.currentFuel ? 1 : -1;
=======
	public double getFuelConsump() {
		return this.fuelConsump;
	}

	protected void fuelCalculate(double orderReduced) {
		this.currentFuel = this.currentFuel - this.fuelConsump + (this.fuelConsump * orderReduced / 100);
>>>>>>> 77efec613a243525b743ff1c7533bf1e2a022864
	}
}
