set term pdfcairo size 8.5,8.5 font "Times-New-Roman, 25"
set xlabel "Number of Trucks in the Platoon (n)"
set xtics 2,1,6
set key center right
set grid

set ylabel "Average Total Distance"
set yrange [0:3800]
set output "rand-dist.pdf"
plot "rand-avg-120.dat" using 1:2 w lp lw 2 title "2 Hours",\
"rand-avg-60.dat" using 1:2 w lp lw 2 title "1 Hour",\
"rand-avg-30.dat" using 1:2 w lp lw 2 title "30 Minutes"

set ylabel "Average Position Change Count"
set yrange [0:20]
set output "rand-cnt.pdf"
plot "rand-avg-120.dat" using 1:3 w lp lw 2 title "2 Hours",\
"rand-avg-60.dat" using 1:3 w lp lw 2 title "1 Hour",\
"rand-avg-30.dat" using 1:3 w lp lw 2 title "30 Minutes"