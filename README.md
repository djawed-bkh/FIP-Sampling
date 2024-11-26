# FIP Sampling

FIP Sampling (FIPS) is an approach for interval pattern sampling dedicated to numerical datasets, proportionally to their frequency. This repository provides everything necessary for executing the binaries and conducting the experimental evaluations.

## Content of the repository:
1. **Source Code for FIPS:** Implementation of the FIPS sampling method (see src directory).
2. **Source Code for uniform Sampling (With Coverage):** A method for sampling interval patterns uniformly, ensuring non-empty coverage (see src directory).
3. **Source Code for uniform Sampling (Without Coverage):** A method for uniform interval pattern sampling that does not guarantee coverage (see src directory).
4. **Jar file for lunching experiments** A JAR file is provided to run all the experiments presented in the article by following the commands outlined above([Jar directory](https://anonymous.4open.science/r/EGC2025-8153/FIP_Sampling.jar)). 
5. **Benchmark Datasets:** The datasets used in the experimental protocol (see benchmark directory).
6. **Experimental Results:** Text files containing the results for all datasets (see Results directory).
7. **Graphical Results:** Visual representations of the experimental results for each dataset ([PDF file](https://anonymous.4open.science/r/EGC2025-8153/Annexe_EGC_2025.pdf) ).




## Command Lines for Running Experiments

### 1. Database Statistics
To get statistics on a specific numerical dataset:

```bash
java -jar FIP_Sampling.jar DatabaseStatistics <path_of_the_desired_numerical_dataset>
```

### 2. Draw an interval pattern with FIPS
To draw interval patterns using the FIPS method:
```bash
java -jar FIP_Sampling.jar drawfips <path of the desired numerical dataset>
```


### 3.  Draw an interval pattern with a random approach that ensure a non empty coverage
To draw interval patterns using the random IP sampling method:
```bash
java -jar FIP_Sampling.jar drawrandomip <path of the desired numerical dataset>
```


### 4. Draw an interval pattern with a random approach which does not ensure non empty coverage
To draw interval patterns using the totally random IP sampling method:
```bash
java -jar FIP_Sampling.jar drawtotalyrandomip <path of the desired numerical dataset>
```

### 5. Evaluate the frequency based plausibility for FIPS
To evaluate the plausibility of interval patterns drawn with FIPS:
```bash
java -jar FIP_Sampling.jar evaluateplausibilityfips <path of the desired numerical dataset> <Number of Interval patterns (fixed to 100 000 in the paper)> <Number of randomised databases (fixed to 10 in the paper) > <Minimum frequency threshold (between 0.0 and 1.0)> <Maximum frequency threshold(between 0.0 and 1.0)>
```

### 6. Evaluate the frequency based plausibility for the uniform interval pattern sampling
To evaluate the plausibility of random interval patterns:
```bash
java -jar FIP_Sampling.jar evaluateplausibilityrandomip <path of the desired numerical dataset> <Number of Interval patterns (fixed to 100 000 in the paper)> <Number of randomised databases (fixed to 10 in the paper)> <Minimum frequency threshold(between 0.0 and 1.0)> <Maximum frequency threshold(between 0.0 and 1.0)>
```

### 7. CPU time (s)  evolution when sampling with FIPS
To track CPU time evolution for FIPS:
```bash
java -jar FIP_Sampling.jar cpu_evolution_fips <path of the desired numerical dataset> <Number of interval pattern desired (fixed to 500 in the paper)> <Path for the output results>
```

### 8. CPU time (s)  evolution when sampling with a uniform interval pattern sampling approach
To track CPU time evolution for random interval patterns:
```bash
 java -jar FIP_Sampling.jar cpu_evolution_randomip <path of the desired numerical dataset> <Number of Interval patterns (fixed to 500 in the paper)>
```

### 9. Diversity evaluation for FIPS
To evaluate the diversity of interval patterns drawn with FIPS:
```bash
java -jar FIP_Sampling.jar diversityfips <path of the desired numerical dataset> <Number of Interval patterns (10 000 fixed to 100 000 in the paper)>
```



### 10. Diversity evaluation for the uniform interval pattern sampling approach
To evaluate the diversity of random interval patterns:
```bash
java -jar FIP_Sampling.jar diversityrandomip <path of the desired numerical dataset> <Number of Interval patterns (10 000 fixed to 100 000 in the paper)>
```
