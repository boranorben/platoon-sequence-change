set term pdfcairo size 8.5,8.5 font "Times-New-Roman, 25"
set xlabel "Switching period (minute)"
set ylabel "Total distance (kilometer)"
set grid
set key outside

set output "short-interval-dist.pdf"
plot "short-interval-dist-2.dat" using 1:2 w lp lw 2 title "2 Trucks",\
"short-interval-dist-3.dat" using 1:2 w lp lw 2 title "3 Trucks",\
"short-interval-dist-4.dat" using 1:2 w lp lw 2 title "4 Trucks",\
"short-interval-dist-5.dat" using 1:2 w lp lw 2 title "5 Trucks",\
"short-interval-dist-6.dat" using 1:2 w lp lw 2 title "6 Trucks"

set ylabel "Sequence changing rounds (s)"
set output "short-interval-cnt.pdf"
plot "short-interval-cnt-2.dat" using 1:3 w lp lw 2 title "2 Trucks",\
"short-interval-cnt-3.dat" using 1:3 w lp lw 2 title "3 Trucks",\
"short-interval-cnt-4.dat" using 1:3 w lp lw 2 title "4 Trucks",\
"short-interval-cnt-5.dat" using 1:3 w lp lw 2 title "5 Trucks",\
"short-interval-cnt-6.dat" using 1:3 w lp lw 2 title "6 Trucks"