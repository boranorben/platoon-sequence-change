package main;

import java.util.ArrayList;

public class TravelingTime extends DrivingThread {

	public TravelingTime(ArrayList<Truck> platoon, String duration, String rand) {
		super(platoon, duration, rand);
	}

	@Override
	public void run() {
		int numTruck = this.cPlatoon.size();

		while (this.isDriving) {
			try {
				Thread.sleep(0L);
			} catch (Exception e) {
				e.getStackTrace();
			}
			
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

		System.out.printf("wo %d %s %d %d \n", this.cPlatoon.size(), this.duration, this.distanceCnt - 1,
				getSwitchCnt(this.orders));

		this.sortTimeSlot();
	}

}
