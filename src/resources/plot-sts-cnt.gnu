set term pdfcairo size 8.5,8.5 font "Times-New-Roman, 25"
set xlabel "Number of Trucks in the Platoon (n)"
set ylabel "Position Change Count"
set xtics 2,1,6
set yrange [0:100]
set key top left
set grid

set output "wo-sts-cnt.pdf"
plot "wo-sts-120.dat" using 1:3 w lp lw 2 title "2 Hours",\
"wo-sts-60.dat" using 1:3 w lp lw 2 title "1 Hour",\
"wo-sts-30.dat" using 1:3 w lp lw 2 title "30 Minutes"

set output "with-sts-cnt.pdf"
plot "with-sts-120.dat" using 1:3 w lp lw 2 title "2 Hours",\
"with-sts-60.dat" using 1:3 w lp lw 2 title "1 Hour",\
"with-sts-30.dat" using 1:3 w lp lw 2 title "30 Minutes"