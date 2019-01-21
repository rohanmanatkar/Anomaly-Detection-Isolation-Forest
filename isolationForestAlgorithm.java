import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class isolationForestAlgorithm {
	
	/*
	 * Function takes input - data, number of trees and sub sampling size 
	 * and returns a list of trees. Each tree is created for a random sub sample size of input data.
	 * */
	public List<Tree> iForest(List<List<Double>> X, int t, int subSampleSize) {
		
		List<Tree> forest = new ArrayList<Tree>();
		int heightLimit = (int)Math.ceil(Math.log(subSampleSize) / Math.log(2));
		
		List<List<Double>> Xsub = new ArrayList<List<Double>>();
		for(int i = 1; i < t; i++) {

			Random rand = new Random();
			int randVal;
			for(int k = 0; k < subSampleSize; k++) {
				randVal = rand.nextInt(X.size());
				Xsub.add(X.get(randVal));
			}
			
			forest.add(iTree(Xsub, 0, heightLimit));
		}
		return forest;
	}
	/*
	 * Function takes input - random sub sample input (X), current tree height (e), and height limit (l)
	 * Returns a tree for X. The external node for the tree holds the size of remaining input.
	 * */
	public Tree iTree(List<List<Double>> X, int e, int l) {
		
		if(e > l || X.size() <= 1) {
			Tree exNode = new Tree(0, 0);
			exNode.setLeft(null);
			exNode.setRight(null);
			exNode.setSize(X.size());
			return exNode;
		}
		else {
			Random rand = new Random();
			int q = rand.nextInt(X.get(0).size());
			List<Double> columnData = new ArrayList<Double>();
			for(List<Double> s : X) {
				columnData.add(s.get(q));
			}
			double minVal = Collections.min(columnData);
			double maxVal = Collections.max(columnData);
			
			double p = (Math.random()*((maxVal - minVal) + 1)) + minVal;
			
			List<List<Double>> X_left = new ArrayList<List<Double>>();
			List<List<Double>> X_right = new ArrayList<List<Double>>();
			
			for(List<Double> row : X) {
				if(row.get(q) < p) {
					X_left.add(row);
				}
				else {
					X_right.add(row);
				}
			}
			Tree inNode = new Tree(q, p);
			inNode.setLeft(iTree(X_left, e + 1, l));
			inNode.setRight(iTree(X_right, e + 1, l));
			
			return inNode;
		}
	}
}
