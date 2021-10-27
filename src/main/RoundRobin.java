package main;

import java.util.ArrayList;

public class RoundRobin extends DrivingThread {

	public RoundRobin(ArrayList<Truck> platoon, String duration, String rand) {
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

			if (getDurationStatement()) {
				this.cPlatoon.add(this.cPlatoon.get(0));
				this.cPlatoon.remove(0);
			}

			for (int i = 0; i < numTruck; i++) {
				Truck currTruck = this.cPlatoon.get(i);

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
