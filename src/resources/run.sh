# Create RUNNABLE JAR file
make

platoonlist=(2 3 4 5 6)
durationlist=(10 20 30 40 50 60 120)

# Run simulation
for platoon in ${platoonlist[@]}
do
	for duration in ${durationlist[@]}
	do
		make numTruck=$platoon duration=$duration jar >> output-$platoon-platoon.dat
	done
done

# Analysis
cat output* | grep wo | awk '{print $2, $3, $4, $5}' > wo-sts.dat
cat output* | grep with | awk '{print $2, $3, $4, $5}' > with-sts.dat

for duration in ${durationlist[@]}
do
	if (( $duration % 30 == 0 ))
	then
		cat wo-sts.dat | grep $duration | awk '{print $1, $3, $4}' > wo-sts-$duration.dat
		cat with-sts.dat | grep $duration | grep -v "30$" | awk '{print $1, $3, $4}' > with-sts-$duration.dat
	fi
done

for platoon in ${platoonlist[@]}
do
	cat with-sts.dat | grep "^$platoon" | grep -v 120 | awk '{print $2, $3, $4}' > short-ts-$platoon.dat
done

cat plot-sts-cnt.gnu | gnuplot
cat plot-short-ts.gnu | gnuplot
rm -rf *.dat