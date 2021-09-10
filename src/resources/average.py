import sys
import numpy as np
from sys import stdin

def cal_perc(x, y):
  return ((x / y) * 100) - 100

perc, counts,  = {}, {}
for line in stdin:
  [platoon, dist, cnt, ori_dist] = line.split()
  platoon, dist, cnt, ori_dist = int(platoon), int(dist), int(cnt), int(ori_dist)
  if platoon not in perc and platoon not in counts:
    perc[platoon], counts[platoon],  = [], []
  perc[platoon].append(cal_perc(dist, ori_dist))
  counts[platoon].append(cnt)
for platoon in sorted(perc.keys()):
  print("%d %.2f %.2f"%(platoon, np.average(perc[platoon]), np.average(counts[platoon])))