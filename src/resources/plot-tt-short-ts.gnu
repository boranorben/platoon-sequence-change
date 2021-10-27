set term pdfcairo enhanced size 8.5,8.5 font "Times-New-Roman, 25"
set xlabel "Size of Time Slot, {/Symbol d} (min)"
set grid
set key center right

set ylabel "Total Distance (km)"
set yrange [0:4000]
set output "tt-short-dist.pdf"
plot "tt-short-ts-2.dat" using 1:2 w lp lw 2 title "2 Trucks",\
"tt-short-ts-3.dat" using 1:2 w lp lw 2 title "3 Trucks",\
"tt-short-ts-4.dat" using 1:2 w lp lw 2 title "4 Trucks",\
"tt-short-ts-5.dat" using 1:2 w lp lw 2 title "5 Trucks",\
"tt-short-ts-6.dat" using 1:2 w lp lw 2 title "6 Trucks"

set ylabel "Position Change Count"
set yrange [0:250]
set output "tt-short-cnt.pdf"
plot "tt-short-ts-2.dat" using 1:3 w lp lw 2 title "2 Trucks",\
"tt-short-ts-3.dat" using 1:3 w lp lw 2 title "3 Trucks",\
"tt-short-ts-4.dat" using 1:3 w lp lw 2 title "4 Trucks",\
"tt-short-ts-5.dat" using 1:3 w lp lw 2 title "5 Trucks",\
"tt-short-ts-6.dat" using 1:3 w lp lw 2 title "6 Trucks"