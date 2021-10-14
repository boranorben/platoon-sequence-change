set term pdfcairo size 8.5,8.5 font "Times-New-Roman, 25"
set xlabel "Initial Fuels in Gallon (F(0))"
set grid

set ylabel "Position Change Count"
set ytics 0,1,6

set output "rr.pdf"
plot "rr-120.dat" using 1:2 w lp lw 2 title "2 Hours",\
"rr-60.dat" using 1:2 w lp lw 2 title "1 Hour",\
"rr-30.dat" using 1:2 w lp lw 2 title "30 Minutes"