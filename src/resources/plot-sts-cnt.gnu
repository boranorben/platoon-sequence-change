set term pdfcairo size 8.5,8.5 font "Times-New-Roman, 25"
set xlabel "Number of trucks in a platoon (n)"
set ylabel "Sequence changing rounds (s)"
set xtics 2,1,6
set yrange [0:100]
set key top left
set grid

set output "without-sts-cnt.pdf"
plot "without-sts-cnt-120.dat" using 1:3 w lp lw 2 title "2 Hours",\
"without-sts-cnt-60.dat" using 1:3 w lp lw 2 title "1 Hour",\
"without-sts-cnt-30.dat" using 1:3 w lp lw 2 title "30 Minutes"

set output "with-sts-cnt.pdf"
plot "with-sts-cnt-120.dat" using 1:3 w lp lw 2 title "2 Hours",\
"with-sts-cnt-60.dat" using 1:3 w lp lw 2 title "1 Hour",\
"with-sts-cnt-30.dat" using 1:3 w lp lw 2 title "30 Minutes"