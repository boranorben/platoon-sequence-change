package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Platoon {
	private ArrayList<Truck> platoon;

	public Platoon(int numTruck, String duration, String rand) {
		this.platoon = new ArrayList<Truck>();
		String[] randList = rand.split(",");

		for (int i = 0; i < numTruck; i++) {
			if (rand.equals("null")) {
				this.platoon.add(new Truck(String.valueOf(i + 1), null));
			} else if (randList.length > 1) {
				this.platoon.add(new Truck(String.valueOf(i + 1), Double.parseDouble(randList[i])));
			} else {
				this.platoon.add(new Truck(String.valueOf(i + 1), Double.parseDouble(rand) * 3.785));
			}
		}
		// fix again
		DrivingThread tThread = new TravelingTime(platoon, duration, rand);
		Thread firstThread = new Thread(tThread);
		firstThread.start();

//		DrivingThread rThread = new RoundRobin(platoon, duration, rand);
//		Thread secondThread = new Thread(rThread);
//		secondThread.start();
	}

	public ArrayList<Truck> getPlatoon() {
		return this.platoon;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Input a number of truck in a platoon: ");
		int numTruck = Integer.parseInt(input.nextLine());
		System.out.print("Input minutes of duration switching: ");
		String duration = input.nextLine();
		new Platoon(numTruck, duration, "null");

		// for runnable jar file
		// args[0]: number of trucks -> 2 to 6
		// args[1]: duration or switching -> 10 20 .. 120
		// args[2]: randList -> null if no rand
//		new Platoon(Integer.parseInt(args[0]), args[1], args[2]);
	}
}