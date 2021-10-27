set term pdfcairo size 8.5,8.5 font "Times-New-Roman, 25" enhanced
set xlabel "Number of Trucks in the Platoon (n)"
set ylabel "Position Change Count"
set xtics 2,1,6
set yrange [0:80]
set key top left
set grid

set output "rr-cnt.pdf"
plot "tt-with-sts-120.dat" using 1:3 w lp lw 2 title "{/Symbol d} = 2 Hours, without RR",\
"tt-with-sts-60.dat" using 1:3 w lp lw 2 title "{/Symbol d} = 1 Hour, without RR",\
"tt-with-sts-30.dat" using 1:3 w lp lw 2 title "{/Symbol d} = 30 Minutes, without RR",\
"rr-with-sts-120.dat" using 1:3 w lp lw 2 title "{/Symbol d} = 2 Hours, with RR",\
"rr-with-sts-60.dat" using 1:3 w lp lw 2 title "{/Symbol d} = 1 Hour, with RR",\
"rr-with-sts-30.dat" using 1:3 w lp lw 2 title "{/Symbol d} = 30 Minutes, with RR"