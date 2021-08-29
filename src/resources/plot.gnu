set term pdfcairo size 8.5,8.5 font "Times-New-Roman, 25"
set xlabel "Number of trucks in a platoon (n)"
set ylabel "Sequence changing rounds (s)"
set xtics 2,1,6
set yrange [0:100]
set key top left
set grid

set output "switch-wo-sort.pdf"
plot "without-120.dat" using 1:3 w lp lw 2 title "2 Hours",\
"without-60.dat" using 1:3 w lp lw 2 title "1 Hour",\
"without-30.dat" using 1:3 w lp lw 2 title "30 Minutes"

set output "switch-w-sort.pdf"
plot "with-120.dat" using 1:3 w lp lw 2 title "2 Hours",\
"with-60.dat" using 1:3 w lp lw 2 title "1 Hour",\
"with-30.dat" using 1:3 w lp lw 2 title "30 Minutes"