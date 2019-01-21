import java.util.List;

public class CalculatePath {
	/*
	 * Function calculates path length for each instance in given tree.
	 * */
	public double PathLength(List<Double> instance, Tree T, int e) {
		if(T.getLeft() == null && T.getRight() == null) {
			if(T.getSize() > 1) {
				return e + ((2 * (Math.log(T.getSize() - 1) + 0.5772156649)) - ((2 * (T.getSize() - 1)) / T.getSize()));
			}	
			else {
				return e;
			}		
		}
		int attr = T.getAttribute();
		double x_attr = instance.get(attr);
		if(x_attr < T.getSplitVal()) {
			return PathLength(instance, T.getLeft(), e + 1);
		}
		else {
			return PathLength(instance, T.getRight(), e + 1);
		}		
	}
}
