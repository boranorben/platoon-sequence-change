package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Platoon {
	private ArrayList<Truck> platoon;
	private double[] RANGE = { 1135.62, 1324.89 }; // 300 gallons to 350 gallons

	public Platoon(int numTruck, String duration) {
		this.platoon = new ArrayList<Truck>();
		for (int i = 0; i < numTruck; i++) {
			Random random = new Random();
			double randomFuel = RANGE[0] + (RANGE[1] - RANGE[0]) * random.nextDouble();
			this.platoon.add(new Truck(String.valueOf(i + 1), randomFuel));
//			this.platoon.add(new Truck(String.valueOf(i + 1), null));
		}
		DrivingThread dThread = new DrivingThread(this.platoon, duration);
		Thread thread = new Thread(dThread);
		thread.start();
	}

	public ArrayList<Truck> getPlatoon() {
		return this.platoon;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Input a number of truck in a platoon: ");
		int numTruck = Integer.parseInt(input.nextLine());
		System.out.print("Input duration of switching: ");
		String duration = input.nextLine();
		new Platoon(numTruck, duration);

//		for (int i = 2; i <= 6; i++) {
//			for (int j = 0; j <= 4; j++) {
//				new Platoon(i, j + "");
//			}
//		}
	}
}

class DrivingThread implements Runnable {
	private ArrayList<Truck> platoon;
	private ArrayList<Truck> cPlatoon;
	private String duration;
	private boolean isDriving;
	private int distanceCnt;
	private int switchCnt;
	private int index;
	private ArrayList<String> orders;
	private ArrayList<String> checkOrders;
	private String order;

	private final double[] CONSUMP_REDUCE = { 4.3, 10, 14 };
	private final double VELOSITY = 85; // 85 km/hour

	public DrivingThread(ArrayList<Truck> platoon, String duration) {
		this.platoon = platoon;
		this.cPlatoon = platoon;
		this.duration = duration;
		this.orders = new ArrayList<String>();
		this.checkOrders = new ArrayList<String>();

		this.cPlatoon = clonePlatoon(this.platoon);

		initialParams();
	}

	private void initialParams() {
		this.isDriving = true;
		this.distanceCnt = 0;
		this.switchCnt = 0;
		this.index = 0;
	}

//	private boolean getDurationStatement(Truck currTruck, Truck nextTruck) {
	private boolean getDurationStatement() {
		boolean durationStatement;
		if (this.duration.contains("1")) { // 1 hour
			durationStatement = (getCurrTime() == Math.floor(getCurrTime()));
		} else if (this.duration.contains("2")) { // 2 hours
			durationStatement = (getCurrTime() == Math.floor(getCurrTime()) && Math.floor(getCurrTime()) % 2 == 0);
		} else if (this.duration.contains("3")) { // 30 mins
			durationStatement = ((roundTime(getCurrTime()) * 10) % 5 == 0);
		} else if (this.duration.contains("4")) { // 10 mins
			durationStatement = ((roundTime(getCurrTime()) * 10) % 1 == 0);
		} else {
			durationStatement = false;
		}

//		if (this.duration.contains("1")) {
//			durationStatement = (getCurrTime() == Math.floor(getCurrTime()));
//		} else {
//			durationStatement = (getCurrTime() == Math.floor(getCurrTime())
//					|| (roundTime(getCurrTime()) - 0.5) % 1 == 0);
//		}

//		if (this.duration.contains("1")) { // full rate
//			durationStatement = (nextTruck.getCurrentFuel() - currTruck.getCurrentFuel()) >= currTruck.FUEL_CONSUMP * 100;
//		} else if (this.duration.contains("2")) { // half rate
//			durationStatement = nextTruck.getCurrentFuel()
//					- currTruck.getCurrentFuel() >= ((currTruck.FUEL_CONSUMP * 100) / 2);
//		} else if (this.duration.contains("3")) { // 1 hour
//			durationStatement = (getCurrTime() == Math.floor(getCurrTime()));
//		} else if (this.duration.contains("4")) { // 2 hours
//			durationStatement = (getCurrTime() == Math.floor(getCurrTime()) && Math.floor(getCurrTime()) % 2 == 0);
//		} else {
//			durationStatement = false;
//		}
		return durationStatement;
	}

	private void setOrder() {
		if (this.duration.contains("1")) {
			this.order = this.orders.get((int) this.getCurrTime());
		} else {
			this.order = this.orders.get(this.index);
		}
	}

	private ArrayList<Truck> clonePlatoon(ArrayList<Truck> rPlatoon) {
		ArrayList<Truck> cPlatoon = new ArrayList<Truck>();
		Iterator<Truck> iterator = rPlatoon.iterator();
		while (iterator.hasNext()) {
			Truck truck = iterator.next();
			Truck cTruck = new Truck(truck.getOrder(), truck.getCurrentFuel());
			cPlatoon.add(cTruck);
		}
		return cPlatoon;
	}

	private void switchPos(ArrayList<Truck> platoon) {
		Collections.sort(platoon);
	}

	private void swapPos(ArrayList<Truck> platoon, int i, int j) {
		Collections.swap(platoon, i, j);
	}

	private double getConsumpReduce(int i) {
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

	private double getCurrTime() {
		return this.distanceCnt / VELOSITY;
	}

	private void stopDriving() {
		this.isDriving = false;
	}

	private void saveOrderIdx(ArrayList<String> orders, ArrayList<Truck> platoon) {
		String order = "";
		for (Truck truck : platoon) {
			order += truck.getOrder();
		}
		orders.add(order);
	}

	private void sortOrders() {
		Collections.sort(this.orders);
	}

	private int getSwitchCnt(ArrayList<String> order) {
		int cnt = 0;
		for (int i = 0; i < order.size() - 1; i++) {
			if (!order.get(i).equals(order.get(i + 1))) {
				cnt++;
			}
		}
		return cnt;
	}

	private void printStatus(ArrayList<Truck> platoon) {
		for (Truck truck : platoon) {
			System.out.printf("[Hour %.1f]: Truck at %s ordered has %.2f fuel remains \n", getCurrTime(),
					truck.getOrder(), truck.getCurrentFuel());
		}
		System.out.println("---------------------------------------------------");
	}

	private void printOrder(ArrayList<String> orders) {
		for (String order : orders) {
			System.out.println(order);
		}
	}

	private double roundTime(double time) {
		double rounded = time;
		rounded *= 100;
		rounded = (double) ((int) rounded);
		rounded /= 100;
		return rounded;
	}

	@Override
	public void run() {

		while (this.isDriving) {
			try {
				Thread.sleep(1L);
			} catch (Exception e) {
				e.getStackTrace();
			}

			if (getDurationStatement()) {
				printStatus(this.cPlatoon);
			}

			for (int i = 0; i < this.cPlatoon.size(); i++) {
				Truck currTruck = this.cPlatoon.get(i);

				for (int j = i + 1; j < this.cPlatoon.size(); j++) {
					Truck nextTruck = this.cPlatoon.get(j);

//					if (getDurationStatement(currTruck, nextTruck)) {
					if (getDurationStatement()) {
						switchPos(this.cPlatoon);
					}
				}
				currTruck.fuelCal(getConsumpReduce(i));
				if (currTruck.getCurrentFuel() <= 0) {
					stopDriving();
					break;
				}
			}

//			if (getCurrTime() == Math.floor(getCurrTime())) {
			if (getDurationStatement()) {
				saveOrderIdx(this.orders, this.cPlatoon);
			}
			distanceCnt++;
		}
		System.out.printf("Distance %d km \n", this.distanceCnt - 1);
		System.out.printf("Time %.0f hr \n", getCurrTime());
		System.out.println("# of switching " + getSwitchCnt(this.orders));
//		System.out.println("# of switching " + this.switchCnt);

		initialParams();
		sortOrders();
//		printOrder(this.orders);
		System.out.println("---------------------------------------------------");

		while (isDriving) {
			try {
				Thread.sleep(1L);
			} catch (Exception e) {
				e.getStackTrace();
			}

//			if (getDurationStatement()) {
//				printStatus(this.platoon);
//			}
			if (this.index > this.orders.size() - 1) {
				stopDriving();
				break;
			}

			setOrder();
			for (int i = 0; i < this.platoon.size(); i++) {
				Truck currTruck = this.platoon.get(i);

				for (int j = i + 1; j < this.platoon.size(); j++) {
					Truck nextTruck = this.platoon.get(j);

					if (getDurationStatement()) {
						if (!String.valueOf(order.charAt(i)).equals(currTruck.getOrder())) {
							if (String.valueOf(order.charAt(i)).equals(nextTruck.getOrder())) {
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
		System.out.println("---------------------------------------------------");

		System.out.println("Are orders correct?: " + this.orders.equals(this.checkOrders));
		System.out.printf("Distance %d km \n", this.distanceCnt - 1);
		System.out.printf("Time %.0f hr \n", getCurrTime());
//		System.out.println("# of switching " + this.switchCnt);
		System.out.println("# of switching " + getSwitchCnt(this.checkOrders));
	}

}