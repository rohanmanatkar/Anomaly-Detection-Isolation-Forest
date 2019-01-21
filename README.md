# Anomaly-Detection-Isolation-Forest
Implementation of Isolation Forest Algorithm for anomaly detection in Java

Input - input from stdin with first row taking an integer value that denotes the number of anomalies
        to be returned in output followed by data points.
        
Output - Set of anomalies with their data points and anomalyScore.

The anomaly score s(x, n) = 2^(-E(h(x))/c(n)) where c(n) = 2H(n-1) – 2(n-1)/n is the average
of h(x) given n, H(i) is the harmonic number as ln(i) + 0.5772156649 (Euler’s
constant), and h(x) is the path length. E(h(x)) is the average h(x) of from a collection
of isolation trees. 0 < s ≤ 1 for 0 < h(x) ≤ n -1. If s close to 1, then they are definitely
anomalies, if s much smaller than 0.5, they are normal instances, and if all instances
return s ≈ 0.5, then the entire sample doesn’t really have any anomaly

Files
1. P1.java - Main method
2. isolationForestAlgorithm.java - Contains methods iForest and iTree.

                                   iForest - Function takes input - data, number of trees and sub sampling size
                                   and returns a list of trees. Each tree is created for a random sub sample size of input data.
                              
                                   iTree - Function takes input - random sub sample input (X), current tree height (e), and height limit (l). 
                                   Returns a tree for X. The external node for the tree holds the size of remaining input.
                              
3. CalculatePath.java - Function calculates path length for each instance in given tree.
4. Tree.java - Structure of Tree object defined.
5. instanceAnomaly.java - Structure of anomaly object that returns data point and it's anomaly score is defined.

To run the code, Run the main method and give input as described above with each data point on a new line with comma separated values.
Press CTRL-D on Windows(CMD-C/CTRL-C on MAC/Linux) to stop adding input data.
