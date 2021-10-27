# Create RUNNABLE JAR file
make

platoonlist=(2 3 4 5 6)
durationlist=(10 20 30 40 50 60 120)
initialfuel=(100 150 200 250 300)

# Run simulation with TT
for platoon in ${platoonlist[@]}
do
	# Run simulation with equal initial fuel
	for duration in ${durationlist[@]}
	do
		make algo='tt' numTruck=$platoon duration=$duration randList='null' jar >> output-$platoon.dat
	done

	# Run simulation with random initial fuel
	for (( i = 0; i < 10; i++ ))
	do

		randnum=""
		for (( j = 0; j < $platoon; j++ ))
		do
			# 250 gallons to 300 gallons	
			rand=$(python -c 'import random; print("%.2f" % random.uniform(946.35, 1135.62));')
			randnum+="$rand,"
		done

		for duration in ${durationlist[@]}
		do
			if (( $duration % 30 == 0 ))
			then	
					make algo='tt' numTruck=$platoon duration=$duration randList=$randnum jar >> rand-output-$platoon.dat
			fi
		done
	done
done

# # Run simulation with RR
# for duration in ${durationlist[@]}
# do
# 	if (( $duration % 30 == 0 ))
# 	then
# 		for fuel in ${initialfuel[@]}
# 		do
# 			make algo='rr' numTruck=6 duration=$duration randList=$fuel jar >> rr-output.dat
# 			cat rr-output.dat | grep with | grep " $duration " | awk '{print $7, $5}' > rr-$duration.dat
# 		done
# 	fi
# done

# Analysis
cat output* | grep wo | awk '{print $2, $3, $4, $5}' > tt-wo-sts.dat
cat output* | grep with | awk '{print $2, $3, $4, $5}' > tt-with-sts.dat
cat rand* | grep with | awk '{print $2, $3, $4, $5, $6}' > tt-rand.dat

for duration in ${durationlist[@]}
do
	if (( $duration % 30 == 0 ))
	then
		cat tt-wo-sts.dat | grep $duration | awk '{print $1, $3, $4}' > tt-wo-sts-$duration.dat
		cat tt-with-sts.dat | grep $duration | grep -v "30$" | awk '{print $1, $3, $4}' > tt-with-sts-$duration.dat

		cat tt-rand.dat | grep " $duration " | awk '{print $1, $3, $4, $5}' > tt-rand-$duration.dat
		python3 average.py < tt-rand-$duration.dat > tt-rand-avg-$duration.dat
	fi
done

for platoon in ${platoonlist[@]}
do
	cat tt-with-sts.dat | grep "^$platoon" | grep -v 120 | awk '{print $2, $3, $4}' > tt-short-ts-$platoon.dat
done

# Plot graphs
cat plot-sts-cnt.gnu | gnuplot
cat plot-short-ts.gnu | gnuplot
cat plot-rand.gnu | gnuplot
# cat plot-rr.gnu | gnuplot

# Delete all data files
rm -rf *.dat
