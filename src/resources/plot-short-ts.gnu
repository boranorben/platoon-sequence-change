set term pdfcairo enhanced size 8.5,8.5 font "Times-New-Roman, 25"
set xlabel "Size of Time Slot, {/Symbol d} (min)"
set ylabel "Total Distance (km)"
set grid
set key outside

set output "short-dist.pdf"
plot "short-ts-2.dat" using 1:2 w lp lw 2 title "2 Trucks",\
"short-ts-3.dat" using 1:2 w lp lw 2 title "3 Trucks",\
"short-ts-4.dat" using 1:2 w lp lw 2 title "4 Trucks",\
"short-ts-5.dat" using 1:2 w lp lw 2 title "5 Trucks",\
"short-ts-6.dat" using 1:2 w lp lw 2 title "6 Trucks"

set ylabel "Position Change Count"
set output "short-cnt.pdf"
plot "short-ts-2.dat" using 1:3 w lp lw 2 title "2 Trucks",\
"short-ts-3.dat" using 1:3 w lp lw 2 title "3 Trucks",\
"short-ts-4.dat" using 1:3 w lp lw 2 title "4 Trucks",\
"short-ts-5.dat" using 1:3 w lp lw 2 title "5 Trucks",\
"short-ts-6.dat" using 1:3 w lp lw 2 title "6 Trucks"