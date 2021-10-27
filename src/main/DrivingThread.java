package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public abstract class DrivingThread implements Runnable {
	protected ArrayList<Truck> platoon;
	protected ArrayList<Truck> cPlatoon;
	protected String duration;
	protected boolean isDriving;
	protected int distanceCnt;
	protected int index;
	protected ArrayList<String> orders;
	protected ArrayList<String> checkOrders;
	protected String order;
	protected double initFuel;

	protected final double[] CONSUMP_REDUCE = { 4.3, 10.0, 14.0 };
	protected final double VELOSITY = 85.0; // 85 km/hour

	public DrivingThread(ArrayList<Truck> platoon, String duration, String rand) {
		this.platoon = platoon;
		this.cPlatoon = platoon;
		this.duration = duration;
		this.orders = new ArrayList<String>();
		this.checkOrders = new ArrayList<String>();

		this.cPlatoon = clonePlatoon(this.platoon);

		initialParams();

		if (rand.equals("null") || rand.length() > 1) {
			this.initFuel = this.platoon.get(0).getCurrentFuel();
		} else {
			this.initFuel = Double.parseDouble(rand);
		}
	}

	protected void initialParams() {
		this.isDriving = true;
		this.distanceCnt = 0;
		this.index = 0;
	}

	protected boolean getDurationStatement() {
		boolean durationStatement;

		switch (this.duration) {
		case "10":
			durationStatement = ((roundTime(getCurrTime()) * 10) % 1 == 0);
			break;
		case "20":
			durationStatement = ((roundTime(getCurrTime()) * 10) % 3 == 0);
			break;
		case "30":
			durationStatement = ((roundTime(getCurrTime()) * 10) % 5 == 0);
			break;
		case "40":
			durationStatement = ((roundTime(getCurrTime()) * 10) % 6 == 0);
			break;
		case "50":
			durationStatement = ((roundTime(getCurrTime()) * 10) % 8 == 0);
			break;
		case "60":
			durationStatement = (getCurrTime() == Math.floor(getCurrTime()));
			break;
		case "120":
			durationStatement = (getCurrTime() == Math.floor(getCurrTime()) && Math.floor(getCurrTime()) % 2 == 0);
			break;
		default:
			durationStatement = false;
			break;
		}

		// old code
//		if (this.duration.contains("1")) { // full rate
//			durationStatement = (nextTruck.getCurrentFuel() - currTruck.getCurrentFuel()) >= currTruck.FUEL_CONSUMP * 100;
//		} else if (this.duration.contains("2")) { // half rate
//			durationStatement = nextTruck.getCurrentFuel() - currTruck.getCurrentFuel() >= ((currTruck.FUEL_CONSUMP * 100) / 2);
//		} else if (this.duration.contains("3")) { // 1 hour
//			durationStatement = (getCurrTime() == Math.floor(getCurrTime()));
//		} else if (this.duration.contains("4")) { // 2 hours
//			durationStatement = (getCurrTime() == Math.floor(getCurrTime()) && Math.floor(getCurrTime()) % 2 == 0);
//		} else {
//			durationStatement = false;
//		}

		return durationStatement;
	}

	protected ArrayList<Truck> clonePlatoon(ArrayList<Truck> rPlatoon) {
		ArrayList<Truck> cPlatoon = new ArrayList<Truck>();
		Iterator<Truck> iterator = rPlatoon.iterator();
		while (iterator.hasNext()) {
			Truck truck = iterator.next();
			Truck cTruck = new Truck(truck.getOrder(), truck.getCurrentFuel());
			cPlatoon.add(cTruck);
		}
		return cPlatoon;
	}

	protected double getOriDist(ArrayList<Truck> platoon) {
		double tempFuel = 0.0;
		for (Truck truck : platoon) {
			tempFuel += truck.getCurrentFuel();
		}

		return ((tempFuel / platoon.size())
				/ (platoon.get(0).FUEL_CONSUMP - ((this.CONSUMP_REDUCE[0] / 100) * platoon.get(0).FUEL_CONSUMP)));
	}

	protected void switchPos(ArrayList<Truck> platoon) {
		Collections.sort(platoon);
	}

	protected void swapPos(ArrayList<Truck> platoon, int i, int j) {
		Collections.swap(platoon, i, j);
	}

	protected double getConsumpReduce(int i) {
		double reducePercent = 0.0;
		switch (i) {
		case 0:
			reducePercent = CONSUMP_REDUCE[0];
			break;
		case 1:
			reducePercent = CONSUMP_REDUCE[1];
			break;
		default:
			reducePercent = CONSUMP_REDUCE[2];
			break;
		}
		return reducePercent;
	}

	protected double getCurrTime() {
		return this.distanceCnt / VELOSITY;
	}

	protected void stopDriving() {
		this.isDriving = false;
	}

	protected void saveOrderIdx(ArrayList<String> orders, ArrayList<Truck> platoon) {
		String order = "";
		for (Truck truck : platoon) {
			order += truck.getOrder();
		}
		orders.add(order);
	}

	protected void sortOrders() {
		Collections.sort(this.orders);
	}

	protected int getSwitchCnt(ArrayList<String> order) {
		int cnt = 0;
		for (int i = 0; i < order.size() - 1; i++) {
			if (!order.get(i).equals(order.get(i + 1))) {
				cnt++;
			}
		}
		return cnt;
	}

	protected void printStatus(ArrayList<Truck> platoon) {
		for (Truck truck : platoon) {
			System.out.printf("[Hour %.1f]: Truck at %s ordered has %.2f fuel remains \n", getCurrTime(),
					truck.getOrder(), truck.getCurrentFuel());
		}
		System.out.println("---------------------------------------------------");
	}

	protected void printOrder(ArrayList<String> orders) {
		for (String order : orders) {
			System.out.println(order);
		}
	}

	protected boolean checkOrders(ArrayList<String> order, ArrayList<String> checkOrder) {
		while (order.size() != checkOrder.size()) {
			order.remove(order.size() - 1);
		}
		return order.equals(checkOrder);
	}

	protected double roundTime(double time) {
		double rounded = time;
		rounded *= 100;
		rounded = (double) ((int) rounded);
		rounded /= 100;
		return rounded;
	}

	protected void sortTimeSlot() {
		initialParams();
		sortOrders();

		double oriDist = getOriDist(this.platoon);
		while (isDriving) {
			try {
				Thread.sleep(0L);
			} catch (Exception e) {
				e.getStackTrace();
			}

			if (this.index > this.orders.size() - 1) {
				stopDriving();
				break;
			} else {
				this.order = this.orders.get(this.index);
			}

//			if (getDurationStatement()) {
//				printStatus(this.platoon);
//			}

			for (int i = 0; i < this.platoon.size(); i++) {
				Truck currTruck = this.platoon.get(i);

				for (int j = i + 1; j < this.platoon.size(); j++) {
					Truck nextTruck = this.platoon.get(j);

					if (getDurationStatement()) {
						if (!String.valueOf(this.order.charAt(i)).equals(currTruck.getOrder())) {
							if (String.valueOf(this.order.charAt(i)).equals(nextTruck.getOrder())) {
								swapPos(this.platoon, i, j);
								break;
							}
						}
					}
				}
				currTruck.fuelCal(getConsumpReduce(i));
				if (currTruck.getCurrentFuel() <= 0) {
					stopDriving();
					break;
				}
			}
			if (getDurationStatement()) {
				saveOrderIdx(this.checkOrders, this.platoon);
				this.index++;
			}
			distanceCnt++;
		}
//		printOrder(this.checkOrders);
//		System.out.println("---------------------------------------------------");

//		System.out.println("Are orders correct?: " + checkOrders(this.orders, this.checkOrders));
//		System.out.printf("Distance with algorithm %d km \n", this.distanceCnt - 1);
//		System.out.printf("Time %.0f hr \n", getCurrTime());
//		System.out.println("# of switching " + this.switchCnt);
//		System.out.println("# of switching with algorithm " + getSwitchCnt(this.checkOrders));
//		System.out.println("---------------------------------------------------");
		System.out.printf("with %d %s %d %d %.0f %.2f\n", this.platoon.size(), this.duration, this.distanceCnt,
				getSwitchCnt(this.checkOrders), oriDist, this.initFuel);
	}

}