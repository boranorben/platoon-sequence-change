package main;

import java.util.ArrayList;

public class TravelingTime extends DrivingThread {

	public TravelingTime(ArrayList<Truck> platoon, String duration, String rand) {
		super(platoon, duration, rand);
	}

	@Override
	public void run() {
		int numTruck = this.cPlatoon.size();
		double oriDist = getOriDist(this.platoon);

		while (this.isDriving) {
			try {
				Thread.sleep(0L);
			} catch (Exception e) {
				e.getStackTrace();
			}

//			if (getCurrTime() != 0.0 && getDurationStatement()) {
//				printStatus(this.cPlatoon);
//			}

			for (int i = 0; i < numTruck; i++) {
				Truck currTruck = this.cPlatoon.get(i);

				for (int j = i + 1; j < numTruck; j++) {
					Truck nextTruck = this.cPlatoon.get(j);
					if (currTruck.getCurrentFuel() < nextTruck.getCurrentFuel() && getDurationStatement()) {
						switchPos(this.cPlatoon);
					}
				}
				currTruck.fuelCal(getConsumpReduce(i));
				if (currTruck.getCurrentFuel() <= 0) {
					stopDriving();
					break;
				}
			}

			if (getDurationStatement()) {
				saveOrderIdx(this.orders, this.cPlatoon);
			}
			distanceCnt++;
		}
//		printOrder(this.orders);
//		System.out.println("---------------------------------------------------");

//		System.out.printf("Distance %d km \n", this.distanceCnt - 1);
//		System.out.printf("Time %.0f hr \n", getCurrTime());
//		System.out.println("# of switching without algorithm " + getSwitchCnt(this.orders));

		System.out.printf("tt_wo %d %s %d %d \n", this.cPlatoon.size(), this.duration, this.distanceCnt - 1,
				getSwitchCnt(this.orders));

		this.sortTimeSlot();

		System.out.printf("tt_with %d %s %d %d %.0f %.2f\n", this.platoon.size(), this.duration, this.distanceCnt,
				getSwitchCnt(this.checkOrders), oriDist, this.initFuel);

	}

}
