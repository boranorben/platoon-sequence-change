# How to run the simulation

Clone this project to your local repository and run these commands.

```
cd ./sequence-change-platoon/src/resources
bash run.sh
```

After the simulation ends, you will get six simulation results in .pdf files described as follows:

| Filename | Description |
| ------ | ------ |
| tt-wo-sts-cnt.pdf | Comparison of position change count without STS |
| tt-with-sts-cnt.pdf | Comparison of position change count with STS |
| tt-short-cnt.pdf | Comparison of position change count when time slots are small |
| tt-short-dist.pdf | Comparison of total distance when time slots are small |
| tt-rand-cnt.pdf | Comparison of average total distance with an unequal intial fuel |
| tt-rand-dist.pdf | Comparison of average position change count with an unequal intial fuel |

**Optional:** You can delete all simulation results files using one command.

```
make clean
```