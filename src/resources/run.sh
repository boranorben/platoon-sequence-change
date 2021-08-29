# Create RUNNABLE JAR file
make

durationlist=(30 60 120)
platoonlist=(2 3 4 5 6)

# Run simulation
for platoon in ${platoonlist[@]}
do
	for duration in ${durationlist[@]}
	do
		make numTruck=$platoon duration=$duration jar >> out-$platoon.dat
	done
done

# Analysis
cat out* | grep old | awk '{print $2, $3, $4, $5}' > with.dat
cat out* | grep new | awk '{print $2, $3, $4, $5}' > without.dat

for duration in ${durationlist[@]}
do
	cat with.dat | grep $duration | awk '{print $1, $3, $4}' > with-$duration.dat
	cat without.dat | grep $duration | awk '{print $1, $3, $4}' > without-$duration.dat
done

rm -rf with.dat without.dat out*
cat plot.gnu | gnuplot