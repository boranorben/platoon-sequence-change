import sys
import numpy as np
from sys import stdin

distances, counts = {}, {}
for line in stdin:
  [platoon, dist, cnt] = line.split()
  platoon, dist, cnt = int(platoon), int(dist), int(cnt)
  if platoon not in distances and platoon not in counts:
    distances[platoon], counts[platoon] = [], []
  distances[platoon].append(dist)
  counts[platoon].append(cnt)

for platoon in sorted(distances.keys()):
  print("%d %.2f %.2f"%(platoon, np.average(distances[platoon]), np.average(counts[platoon])))