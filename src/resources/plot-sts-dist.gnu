set term pdfcairo size 8.5,8.5 font "Times-New-Roman, 25"
set xlabel "Switching period (minute)"
set ylabel "Total distance (kilometer)"
set grid
set key outside

set output "sts-dist.pdf"
plot "with-sts-dist-2.dat" using 1:2 w lp lw 2 title "2 Trucks",\
"with-sts-dist-3.dat" using 1:2 w lp lw 2 title "3 Trucks",\
"with-sts-dist-4.dat" using 1:2 w lp lw 2 title "4 Trucks",\
"with-sts-dist-5.dat" using 1:2 w lp lw 2 title "5 Trucks",\
"with-sts-dist-6.dat" using 1:2 w lp lw 2 title "6 Trucks"