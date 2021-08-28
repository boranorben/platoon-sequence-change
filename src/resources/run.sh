# Create RUNNABLE JAR file
make

durationlist=(30 60 120)
platoonlist=(2 3 4 5 6)

# Run simulation
for duration in ${durationlist[@]}
do
	for platoon in ${platoonlist[@]}
	do
		make numTruck=$platoon duration=$duration jar >> out-$duration.dat
	done
done

# Analysis
cat out* | grep -Eo '[0-9]{1,4}' | tr '\n' ' ' > out-temp.dat
