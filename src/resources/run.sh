#!/bin/sh

# Create RUNNABLE JAR file and run
make

platoonlist=(2 3 4 5 6)

for platoon in ${platoonlist[@]}
do
	make numTruck=$platoon duration=2 jar > temp-$platoon.dat
done

