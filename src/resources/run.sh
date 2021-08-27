#!/bin/sh

# Create RUNNABLE JAR file and run
make

platoonlist=(2 3 4 5 6)
durationlist=(30 60 120)

for platoon in ${platoonlist[@]}
do
	for duration in ${durationlist[@]}
	do
		make numTruck=$platoon duration=$duration jar >> temp-$platoon.dat
	done
done

