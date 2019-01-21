import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class P1 {
	public static void main(String[] args) throws IOException {
		try {
			
			/*
			 * Taking input from console.
			 * Storing first line of data as Integer value that signifies number of anomalies to be printed for 
			 * the data.
			 * */
			BufferedReader fileInput = new BufferedReader(new InputStreamReader(System.in));
			String line = fileInput.readLine();
			if(line == null || line.isEmpty()) { 
				System.out.println("Enter valid input");
				return;
			}
			int k = Integer.parseInt(line.trim());
			
			/*
			 * Storing remaining lines of data that signifies data points.
			 * Error handling done for data points to check uniformity.
			 * */
			List<List<Double>> data = new ArrayList<List<Double>>();
			int ct = 0;
			while((line = fileInput.readLine())!= null) {
				
				String[] s = line.split(",");
				List<Double> values = new ArrayList<Double>();
				for(String str: s) {
					values.add(Double.valueOf(str.trim()));
				}
				if(ct == 0) {
					data.add(values);
					ct = values.size();
				}
				else if (values.size() == ct) {
					data.add(values);
				}
				else {
					throw new Exception();
				}
			}
				
			fileInput.close();
			if(data.size() < k) {
				System.out.println("Not enough data!");
				return;
			}
			/*
			 * Calling isolationForestAlgorithm with input data, number of trees which is roughly taken to be 
			 * 20% of number of data points and sub sampling size which is taken as 10% of number of data points.
			 * */
			isolationForestAlgorithm obj = new isolationForestAlgorithm();
			List<Tree> Forest = obj.iForest(data, data.size() / 5, data.size() / 10);
			
			/*
			 * Calculating anomalyScore for each data point in input data. This is calculated for each data point
			 * by calculating the average of the path length for each point.
			 * */
			List<instanceAnomaly> instances = new ArrayList<instanceAnomaly>();
			CalculatePath path = new CalculatePath();
			double h = 0;
			double E = 0;
			double C = 0;
			double anomalyScore = 0;
			
			for(List<Double> rowData : data) {
				
				h = 0;
				E = 0;
				C = 0;
				anomalyScore = 0;
				for(Tree T : Forest) {
					h += path.PathLength(rowData, T, 0);
				}
				E = h / Forest.size();
				C = ((2 * (Math.log(data.size() - 1) + 0.5772156649)) - ((double)(2 * (data.size() - 1)) / data.size()));
				anomalyScore = Math.pow(2, -1 * (E / C));
				instanceAnomaly e = new instanceAnomaly();
				e.score = anomalyScore;
				e.row = rowData;
				instances.add(e);
				
			}
			/*
			 * Sorting and printing the first k anomalies for input data
			 * */
			Collections.sort(instances, new Comparator<instanceAnomaly>() {
				@Override
				public int compare(instanceAnomaly o1, instanceAnomaly o2) {
					return Double.compare(o2.score, o1.score);
				}
				
			});
			
			for(int i = 0; i < k; i++) {
				System.out.println(instances.get(i).row + " " + instances.get(i).score);
			}
			
		} catch (IOException e) {
			System.out.println("Error occured. Data invalid");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Error occured. Data invalid");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error occured. Data invalid");
			e.printStackTrace();
		}
	}
}
