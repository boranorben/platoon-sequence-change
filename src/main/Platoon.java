package main;

import java.util.ArrayList;
import java.util.Collections;

public class Platoon {
	private ArrayList<Truck> platoon;

	public Platoon(int numTruck) {
		this.platoon = new ArrayList<Truck>();
		for (int i = 0; i < numTruck; i++) {
			this.platoon.add(new Truck(String.valueOf(i + 1)));
		}

		DrivingThread dThread = new DrivingThread(platoon);
		Thread thread = new Thread(dThread);
		thread.start();

		try {
			thread.join();
			System.out.printf("A Platoon of %d trucks\n", this.platoon.size());
			System.out.printf("Total distances: %d Km\n", dThread.getDistCount());
			System.out.printf("Total time: %.2f Hr\n", dThread.getCurrentTime());
			System.out.println("Swap: " + dThread.getSwapCount() + " rounds");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getSize() {
		return this.platoon.size();
	}

	public static void main(String[] args) {
		new Platoon(6);
	}
}

class DrivingThread implements Runnable {
	private ArrayList<Truck> platoon;
	private boolean isDriving;
	private int distCnt = 0;
	private int swapCnt = 0;

	private final double FIRST_REDUCE = 4.3;
	private final double SECOND_REDUCE = 10;
	private final double THIRD_REDUCE = 14;
	private final double VELOCITY = 85;

	public DrivingThread(ArrayList<Truck> platoon) {
		this.platoon = platoon;
		this.isDriving = true;
	}

	public synchronized void stopDriving() {
		this.isDriving = false;
	}

	public int getDistCount() {
		return --this.distCnt;
	}

	public int getSwapCount() {
		return this.swapCnt;
	}

	public double getCurrentTime() {
		return this.distCnt / this.VELOCITY;
	}

	private void swap(int i, int j, ArrayList<Truck> platoon) {
		Collections.swap(platoon, i, j);
	}

	@Override
	public void run() {
		while (isDriving) {
			this.distCnt++;

			try {
				Thread.sleep(1L);
			} catch (InterruptedException e) {
				e.getStackTrace();
			}

			for (int i = 0; i < this.platoon.size(); i++) {
				Truck truck = this.platoon.get(i);
				for (int j = i + 1; j < this.platoon.size(); j++) {
					Truck nextTruck = this.platoon.get(j);
//					if (nextTruck.getCurrentFuel() - truck.getCurrentFuel() >= 32.6) {
//						swap(i, j, platoon);
//						this.swapCnt++;
//					}

//					if (nextTruck.getCurrentFuel() - truck.getCurrentFuel() >= 16.3) {
//						swap(i, j, platoon);
//						this.swapCnt++;
//					}

//					if (this.distCnt % 1000 == 0) {
//						if (truck.getCurrentFuel() < nextTruck.getCurrentFuel()) {
//							swap(i, j, platoon);
//							this.swapCnt++;
//						}
//					}

//					if (this.distCnt % 2000 == 0) {
//						if (truck.getCurrentFuel() < nextTruck.getCurrentFuel()) {
//							swap(i, j, platoon);
//							this.swapCnt++;
//						}
//					}

//					if (Math.floor(getCurrentTime()) % 5 == 0) {
//						if (truck.getCurrentFuel() < nextTruck.getCurrentFuel()) {
//							swap(i, j, platoon);
//							this.swapCnt++;
//						}
//					}
					
					if (Math.floor(getCurrentTime()) % 10 == 0) {
						if (truck.getCurrentFuel() < nextTruck.getCurrentFuel()) {
							swap(i, j, platoon);
							this.swapCnt++;
						}
					}
				}

				switch (i + 1) {
				case 1:
					truck.fuelCalculate(FIRST_REDUCE);
					break;
				case 2:
					truck.fuelCalculate(SECOND_REDUCE);
					break;
				case 3:
					truck.fuelCalculate(THIRD_REDUCE);
					break;
				default:
					truck.fuelCalculate(FIRST_REDUCE * (i + 1 + 0.5));
					break;
				}

				if (truck.getCurrentFuel() <= 0) {
					stopDriving();
					break;
				} else {
//					System.out.printf("Order: %s Truck: %.2f Liters remains \n", truck.getOrder(),
//							truck.getCurrentFuel());
				}

			}
//			System.out.println("--------------------------------");
		}
	}

}