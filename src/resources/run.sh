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
cat output* | grep -e 'wo.* 30' -e 'wo.* 60' -e 'wo.* 120' | awk '{print $2, $3, $4, $5}' > without-sts-cnt.dat
cat output* | grep -e 'with.* 30' -e 'with.* 60' -e 'with.* 120' | awk '{print $2, $3, $4, $5}' > with-sts-cnt.dat
cat output* | grep -e 'wo.* 10' -e 'wo.* 20' -e 'wo.* 30' -e 'wo.* 40' -e 'wo.* 50' -e 'wo.* 60' | awk '{print $2, $3, $4, $5}' > short-interval-dist.dat
cat output* | grep -e 'with.* 10' -e 'with.* 20' -e 'with.* 30' -e 'with.* 40' -e 'with.* 50' -e 'with.* 60' | grep -v '3910 20' | awk '{print $2, $3, $4, $5}' > short-interval-cnt.dat

for duration in ${durationlist[@]}
do
	if (( $duration % 30 == 0 ))
	then	
		cat without-sts-cnt.dat | grep $duration | awk '{print $1, $3, $4}' > without-sts-cnt-$duration.dat
		cat with-sts-cnt.dat | grep $duration | awk '{print $1, $3, $4}' > with-sts-cnt-$duration.dat
	fi
done

for platoon in ${platoonlist[@]}
do
	cat short-interval-dist.dat | grep "^$platoon" | awk '{print $2, $3, $4}' > short-interval-dist-$platoon.dat
	cat short-interval-cnt.dat | grep "^$platoon" | awk '{print $2, $3, $4}' > short-interval-cnt-$platoon.dat
done

cat plot-sts-cnt.gnu | gnuplot
cat plot-short-interval.gnu | gnuplot
rm -rf *.dat