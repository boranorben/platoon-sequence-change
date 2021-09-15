# Create RUNNABLE JAR file
make

platoonlist=(2 3 4 5 6)
durationlist=(10 20 30 40 50 60 120)

for platoon in ${platoonlist[@]}
do

	# Random fuel
	for (( i = 0; i < 5; i++ ))
	do
		randnum=""
		for (( j = 0; j < $platoon; j++ ))
		do
			# 250 gallons to 300 gallons	
			rand=$(python -c 'import random; print("%.2f" % random.uniform(946.35, 1135.62));')
			randnum+="$rand,"
		done
	done

	for duration in ${durationlist[@]}
	do
	
		# Run simulation with equal initial fuel
		make numTruck=$platoon duration=$duration randList='null' jar >> output-$platoon.dat

		if (( $duration % 30 == 0 ))
		then
			# Run simulation with random initial fuel
			make numTruck=$platoon duration=$duration randList=$randnum jar >> rand-output-$platoon.dat
		fi
	done
done

# Analysis
cat output* | grep wo | awk '{print $2, $3, $4, $5}' > wo-sts.dat
cat output* | grep with | awk '{print $2, $3, $4, $5}' > with-sts.dat
cat rand* | grep with | awk '{print $2, $3, $4, $5, $6}' > rand.dat

for duration in ${durationlist[@]}
do
	if (( $duration % 30 == 0 ))
	then
		cat wo-sts.dat | grep $duration | awk '{print $1, $3, $4}' > wo-sts-$duration.dat
		cat with-sts.dat | grep $duration | grep -v "30$" | awk '{print $1, $3, $4}' > with-sts-$duration.dat

		cat rand.dat | grep " $duration " | awk '{print $1, $3, $4, $5}' > rand-$duration.dat
		python3 average.py < rand-$duration.dat > rand-avg-$duration.dat
	fi
done

for platoon in ${platoonlist[@]}
do
	cat with-sts.dat | grep "^$platoon" | grep -v 120 | awk '{print $2, $3, $4}' > short-ts-$platoon.dat
done

# Plot graphs
cat plot-sts-cnt.gnu | gnuplot
cat plot-short-ts.gnu | gnuplot
cat plot-rand.gnu | gnuplot

# # Delete all data files
rm -rf *.dat