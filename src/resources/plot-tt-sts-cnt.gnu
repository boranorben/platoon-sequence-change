set term pdfcairo size 8.5,8.5 font "Times-New-Roman, 25"
set xlabel "Number of Trucks in the Platoon (n)"
set ylabel "Position Change Count"
set xtics 2,1,6
set yrange [0:100]
set key at 6,90
set grid

set output "tt-wo-sts-cnt.pdf"
plot "tt-wo-sts-120.dat" using 1:3 w lp lw 2 title "2 Hours",\
"tt-wo-sts-60.dat" using 1:3 w lp lw 2 title "1 Hour",\
"tt-wo-sts-30.dat" using 1:3 w lp lw 2 title "30 Minutes"

set output "tt-with-sts-cnt.pdf"
plot "tt-with-sts-120.dat" using 1:3 w lp lw 2 title "2 Hours",\
"tt-with-sts-60.dat" using 1:3 w lp lw 2 title "1 Hour",\
"tt-with-sts-30.dat" using 1:3 w lp lw 2 title "30 Minutes"